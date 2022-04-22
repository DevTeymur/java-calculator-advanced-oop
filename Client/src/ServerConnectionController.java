import java.net.*;
import java.io.*;
import java.util.concurrent.CompletableFuture;

public class ServerConnectionController
{	
	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public boolean connected = false;

	public ServerConnectionController(String inetAddress, int port)
	{
		CompletableFuture.supplyAsync(()->connectServer(inetAddress, port));
	}



	public Void connectServer(String inetAddress, int port)
	{
		try
		{
			socket = new Socket(InetAddress.getByName(inetAddress), port);
			
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			
			connected = true;
			Client.showFrame();
		}
		catch (Exception e)
		{
			connected = false;
			new ConnectionWindow(inetAddress, port);
		}
		return null;
	}
	
	public String calculate(String num1real, String num1imag, String num2real, String num2imag, String operator)
	{
		sendData(new String [] {
			num1real,
			num1imag,
			num2real,
			num2imag,
		}, operator);
		return receiveData();
	}

	private String receiveData()
	{
		try
		{
			return (String)input.readObject();
		}
		catch (Exception e)
		{
			return "Connection error";
		}
	}
	
	private void sendData(String [] data, String operator)
	{
		try
		{
			output.writeObject("f");
			output.flush();
			
			for (int i = 0; i < 4; i++)
			{
				output.writeObject(data[i]);
				output.flush();
			}
			
			output.writeObject(operator);
			output.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Void endConnection()
	{
		try
		{
			output.writeObject("t");
			output.flush();
			
			input.close();
			output.close();
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

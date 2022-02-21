import java.net.*;
import java.io.*;

public class ServerConnectionController
{	
	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	
	public ServerConnectionController(String inetAdress, int port)
	{
		try
		{
			socket = new Socket(InetAddress.getByName(inetAdress), port);
			
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
			return null;
		}
	}
	
	private void sendData(String [] datas, String operator)
	{
		try
		{
			output.writeObject(new String("f"));
			output.flush();
			
			for (int i = 0; i < 4; i++)
			{
				output.writeObject(datas[i]);
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
	
	public void endConnection()
	{
		try
		{
			output.writeObject(new String("t"));
			output.flush();
			
			input.close();
			output.close();
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

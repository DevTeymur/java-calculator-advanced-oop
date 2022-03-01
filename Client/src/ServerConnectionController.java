import java.net.*;
import java.util.Arrays;
import java.io.*;

public class ServerConnectionController
{	
	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public boolean connected = false;
	
	public ServerConnectionController(String inetAddress, int port)
	{
		connectServer(inetAddress, port);
	}
	
	public void connectServer(String inetAddress, int port)
	{
		try
		{
			socket = new Socket(InetAddress.getByName(inetAddress), port);
			
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			
			connected = true;
		}
		catch (Exception e)
		{
			connected = false;
		}
	}
	
	public String calculate(String num1real, String num1imag, String num2real, String num2imag, String operator)
	{
		System.out.println(Arrays.toString(new String [] {
			num1real,
			num1imag,
			num2real,
			num2imag,
		}));
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
			String recievedData = (String)input.readObject();
			System.out.println(recievedData);
			return recievedData;
//			return (String)input.readObject();
		}
		catch (Exception e)
		{
			return "Connection error";
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

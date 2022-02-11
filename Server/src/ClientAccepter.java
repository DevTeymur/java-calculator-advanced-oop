import java.io.*;
import java.net.*;
import java.util.*;

public class ClientAccepter extends Thread
{
	private ServerSocket serverSocket;
	private Socket socket;
	
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;
	
	public ClientAccepter(ServerSocket serverSocket, Socket socket)
	{
		this.serverSocket = serverSocket;
		this.socket = socket;
		
		try
		{
			input = new ObjectInputStream(socket.getInputStream());
//			output = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("accepter: A client connected!");
	}
	
	@Override
	public void run()
	{
		try
		{
//			output.writeObject(rand);
//			output.flush();
			
			Complex obj = (Complex) input.readObject();
			System.out.println(obj);
			
			socket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

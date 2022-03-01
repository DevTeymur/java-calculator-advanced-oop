import java.io.*;
import java.net.*;

public class Server
{
	private InetAddress address;
	private ServerSocket serverSocket;
	private Socket socket;
	
	public Server(int port)
	{
		try
		{
			address = InetAddress.getLocalHost();
			serverSocket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void startServer()
	{
		System.out.println("Server started at address: " + address.getHostName());

		while(true)
		{
			try
			{
				socket = serverSocket.accept();
				ClientAccepter accepter = new ClientAccepter(socket);
				accepter.start();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("server: A client connected!");
		}	
	}
	
	public static void main(String[] args)
	{
		Server server =  new Server(1224);
		server.startServer();
	}
}

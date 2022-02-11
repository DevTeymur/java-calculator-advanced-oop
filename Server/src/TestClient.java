import java.io.*;
import java.net.*;
import java.util.*;

public class TestClient {

	public static void main(String[] args)
	{
		Socket socket;
		try
		{
			socket = new Socket(InetAddress.getByName("localhost"), 1224);
		
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//		
			out.writeObject(new Complex(5,-2));
			out.flush();
			out.close();
			System.out.println("I am connected to server");
			
			socket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}

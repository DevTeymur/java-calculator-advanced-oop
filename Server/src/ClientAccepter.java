import java.io.*;
import java.net.*;

public class ClientAccepter extends Thread
{
	private Socket socket;
	
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;
	
	public ClientAccepter(ServerSocket serverSocket, Socket socket)
	{
		this.socket = socket;
		
		try
		{
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		try
		{
			Complex [] numbers = getComplexNumbers();
			String operator = getOperator();
			Complex result = Operate(numbers[0], numbers[1], operator);
			sendComplexNumber(result);
			
			input.close();
			output.close();
			socket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Complex [] getComplexNumbers()
	{
		Complex [] numbers = new Complex[2];
		try
		{
			numbers[0] = (Complex)input.readObject();		
			numbers[1] = (Complex)input.readObject();
			return numbers;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public void sendComplexNumber(Complex number)
	{
		try
		{
			output.writeObject(number);
			output.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getOperator()
	{
		try
		{
			String operator = (String)input.readObject();
			return operator;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Complex Operate(Complex number1, Complex number2, String operator)
	{
		if (operator.equals("+"))
		{
			return number1.summation(number2);
		}
		else if (operator.equals("-"))
		{
			return number1.substraction(number2);
		}
		else if (operator.equals("/"))
		{
			return number1.division(number2);
		}
		else if (operator.equals("*"))
		{
			return number1.multiplication(number2);
		}
		return null;
	}
	
}

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
			while(true)
			{
				boolean signal = getEndingSignal();
				
				if (signal)
					break;
				
				Complex [] numbers = getComplexNumbers();
				if (numbers == null)
				{
					sendComplexNumber("Wrong Input!");
					continue;
				}
				
				String operator = getOperator();
				if (operator == null)
				{
					sendComplexNumber("Wrong Input!");
					continue;
				}
				
				Complex result = Operate(numbers[0], numbers[1], operator);
				if (result == null)
				{
					sendComplexNumber("Wrong Input!");
					continue;
				}
				
				sendComplexNumber(result.toString());
			}
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
			float num1real = Float.parseFloat((String)input.readObject());
			float num1imag = Float.parseFloat((String)input.readObject());
			float num2real = Float.parseFloat((String)input.readObject());
			float num2imag = Float.parseFloat((String)input.readObject());
			
			numbers[0] = new Complex(num1real, num1imag);		
			numbers[1] = new Complex(num2real, num2imag);
			
			return numbers;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public void sendComplexNumber(String number)
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
	
	public boolean getEndingSignal()
	{
		try
		{
			String signal = (String)input.readObject();
			if (signal.equals("t"))
				return true;
			else
				return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return true;
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

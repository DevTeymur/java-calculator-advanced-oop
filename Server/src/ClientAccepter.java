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
				String operator = getOperator();
				
				if (numbers == null || operator == null)
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
		String n1r, n1i, n2r, n2i;
		Complex [] numbers = new Complex[2];
		try
		{
			n1r = (String)input.readObject();
			System.out.println("accepted n1r");
			n1i = (String)input.readObject();
			System.out.println("accepted n1i");
			n2r = (String)input.readObject();
			System.out.println("accepted n2r");
			
			n2i = (String)input.readObject();
			
			System.out.println("accepted n2i");
		}
		catch (Exception e)
		{
			return null;
		}
		
		try
		{
			float num1real = Float.parseFloat(n1r);
			float num1imag = Float.parseFloat(n1i);
			float num2real = Float.parseFloat(n2r);
			float num2imag = Float.parseFloat(n2i);
			
			numbers[0] = new Complex(num1real, num1imag);		
			numbers[1] = new Complex(num2real, num2imag);
		}
		catch (Exception e)
		{
			return null;
		}
		
		return numbers;
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
			System.out.println("accepted op");
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
			System.out.println("accepted signal");
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

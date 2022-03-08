import java.io.*;
import java.net.*;

public class ClientAcceptor extends Thread
{
	private final Socket socket;
	
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;
	
	public ClientAcceptor(Socket socket)
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
					sendData("Wrong Input!");
					continue;
				}

				String result = Operate(numbers[0], numbers[1], operator);

				if (result == null)
				{
					sendData("Wrong Input!");
					continue;
				}
				
				sendData(result);
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
		String strN1R, strN1I, strN2R, strN2I;
		Complex [] numbers = new Complex[2];
		try
		{
			strN1R = (String)input.readObject();
			strN1I = (String)input.readObject();
			strN2R = (String)input.readObject();
			strN2I = (String)input.readObject();			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		try
		{
			float num1real = Float.parseFloat(strN1R);
			float num1imag = Float.parseFloat(strN1I);
			float num2real = Float.parseFloat(strN2R);
			float num2imag = Float.parseFloat(strN2I);
			
			numbers[0] = new Complex(num1real, num1imag);		
			numbers[1] = new Complex(num2real, num2imag);
		}
		catch (NumberFormatException e)
		{
			return null;
		}
		
		return numbers;
	}
	
	public void sendData(String number)
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
			return (String)input.readObject();
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
			return signal.equals("t");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return true;
		}
	}
	
	public String Operate(Complex number1, Complex number2, String operator)
	{
		switch (operator) {
			case "+":
				return number1.summation(number2).toString();
			case "-":
				return number1.subtraction(number2).toString();
			case "*":
				return number1.multiplication(number2).toString();
			case "/":
				try {
					return number1.division(number2).toString();
				} catch (ArithmeticException e) {
					return "Cannot divide by zero";
				}
		}
		return "Wrong Operator!";
	}
	
}

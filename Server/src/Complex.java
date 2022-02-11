import java.io.*;

public class Complex implements Serializable
{
	private static final long serialVersionUID = 1L;

	private float real;
	private float imag;
	
	
	public Complex()
	{
		this.real = 0;
		this.imag = 0;
	}
	
	public Complex(float real, float imag)
	{
		this.real = real;
		this.imag = imag;
	}
	
	public Complex summation(Complex number)
	{
		return new Complex(this.real + number.real, this.imag + number.imag);
	}
	public Complex substraction(Complex number)
	{
		return new Complex(this.real - number.real, this.imag - number.imag);
	}
	
	public Complex multiplication(Complex number)
	{
		float realPart = this.real * number.real - this.imag * number.imag;
		float imagPart = this.real * number.imag + this.imag * number.real;
		return new Complex(realPart, imagPart);
	}
	public Complex division(Complex number) throws ArithmeticException
	{
		float denominator = number.real * number.real + number.imag * number.imag;
		
		if (denominator == 0)
			throw new ArithmeticException();

		float realPart = (this.real * number.real + this.imag * number.imag) / denominator;
		float imagPart = (this.imag * number.real - this.real * number.imag) / denominator;
		return new Complex(realPart, imagPart);
		
	}
	
	public boolean equals(Complex number)
	{
		if (this.real == number.real && this.imag == number.imag)
			return true;
		else
			return false;
	}
	
	public float getReal() {
		return real;
	}
	public void setReal(float real) {
		this.real = real;
	}
	public float getImag() {
		return imag;
	}
	public void setImag(float imag) {
		this.imag = imag;
	}
	
	@Override
	public String toString()
	{
		String imagstr, realstr;
		
		if (imag < 0)
			imagstr = "- " + Math.abs(imag) + " i";
		else
			imagstr = "+ " + imag + " i";
		
		if (real < 0)
			realstr = "- " + Math.abs(real);
		else
			realstr = "" + real;
			
		return realstr + " " + imagstr;
	}
}

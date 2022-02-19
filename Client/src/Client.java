import javax.swing.*;
import java.awt.*;

public class Client {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 590;
	
	public static JFrame frame;
	public static Label opLabel;
	public static InputTextField selectedField;
	public static InputTextField num1RealField;
	public static InputTextField num1ImagField;
	public static InputTextField num2RealField;
	public static InputTextField num2ImagField;

	public static void main(String[] args)
	{
		frame = new JFrame("Complex Number Calculator");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		
		opLabel = new Label(frame, " ", 30, 50, 20, 40);
		
		creatConstLabel(frame, "C1 = ", 25, 25, 60, 30);
		creatConstLabel(frame, " + ", 245, 20, 40, 40);
		creatConstLabel(frame, "*", 440, 15, 10, 60);
		creatConstLabel(frame, " i ", 448, 10, 40, 60);
		
		creatConstLabel(frame, "C2 = ", 25, 70, 60, 60);
		creatConstLabel(frame, " + ", 245, 70, 40, 60);
		creatConstLabel(frame, "*", 440, 75, 10, 60);
		creatConstLabel(frame, " i ", 448, 70, 40, 60);
		
		num1RealField = new InputTextField(frame, 85, 20, 150, 40);
		num1ImagField = new InputTextField(frame, 285, 20, 150, 40);
		num2RealField = new InputTextField(frame, 85, 80, 150, 40);
		num2ImagField = new InputTextField(frame, 285, 80, 150, 40);
		
		selectedField = num1RealField;
		
		generateNumberKeyPad();
		generateOperatorKeyPad();

		frame.setVisible(true);

	}
	
	public static void creatConstLabel(JFrame jframe, String text, int posX, int posY, int width, int height)
	{
		JLabel label = new JLabel(text);
		label.setBounds(posX, posY, width, height);  
	    label.setFont(new Font("Calibri", Font.PLAIN, 20));
	    label.setBackground(jframe.getBackground ());
	    label.setOpaque(true);	    
	    jframe.add(label);
	}
	
	public static void generateNumberKeyPad()
	{
		new NumberButton(frame, "1", 30, 150, 90, 60);
		new NumberButton(frame, "2", 147, 150, 90, 60);
		new NumberButton(frame, "3", 256, 150, 90, 60);

		new NumberButton(frame, "4", 30, 230, 90, 60);
		new NumberButton(frame, "5", 147, 230, 90, 60);
		new NumberButton(frame, "6", 256, 230, 90, 60);

		new NumberButton(frame, "7", 30, 310, 90, 60);
		new NumberButton(frame, "8", 147, 310, 90, 60);
		new NumberButton(frame, "9", 256, 310, 90, 60);

		new NumberButton(frame, ".", 30, 390, 90, 60);
		new NumberButton(frame, "0", 147, 390, 90, 60);
	}
	
	public static void generateOperatorKeyPad()
	{
		new OperationButton(frame, "C", 256, 390, 90, 60);
		
		new OperationButton(frame, "+", 380, 150, 90, 60);
		new OperationButton(frame, "-", 380, 230, 90, 60);
		new OperationButton(frame, "x", 380, 310, 90, 60);
		new OperationButton(frame, "/", 380, 390, 90, 60);
		new OperationButton(frame, "=", 380, 470, 90, 60);
	}

}

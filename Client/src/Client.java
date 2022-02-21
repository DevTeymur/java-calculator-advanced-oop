import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client {
	
	private static final int WIDTH = 500;
	private static final int HEIGHT = 590;
	
	private static ServerConnectionController connectionController;
	
	private static JFrame frame;
	private static Label opLabel;
	
	private static InputTextField selectedField;
	
	private static InputTextField num1RealField;
	private static InputTextField num1ImagField;
	private static InputTextField num2RealField;
	private static InputTextField num2ImagField;
	private static JTextField outputField;

	public static void main(String[] args)
	{
		
		connectionController = new ServerConnectionController("localhost", 1224);
		
		frame = new JFrame("Complex Number Calculator");
		frame.setSize(WIDTH, HEIGHT);
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
		
		outputField = new JTextField();
		outputField.setBounds(30, 470, 316, 60);
		outputField.setFont(new Font("Calibri",Font.PLAIN,30));
	    frame.add(outputField);
		
		generateNumberKeyPad();
		generateOperatorKeyPad();

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent evt)
			{
				Client.connectionController.endConnection();
				System.exit(0);
			}
		});
		
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
		new OperationButton(frame, "*", 380, 310, 90, 60);
		new OperationButton(frame, "/", 380, 390, 90, 60);
		new OperationButton(frame, "=", 380, 470, 90, 60);
	}
	
	public static boolean isSelectedFieldEmpty()
	{
		return selectedField.getText().equals("");
	}
	
	public static void setSelectedTextField(String text)
	{
		selectedField.setText(text);
	}
	
	public static String getSelectedTextField()
	{
		return selectedField.getText();
	}
	
	public static void setOpLabelText(String text)
	{
		opLabel.setText(text);
	}
	
	public static String findResult()
	{
		return connectionController.calculate(
			num1RealField.getText(),
			num1ImagField.getText(),
			num2RealField.getText(),
			num2ImagField.getText(),
			opLabel.getText()
		);
	}
	
	public static void setOutPutText(String text)
	{
		outputField.setText(text);
	}
	
	public static void clearNumberFields()
	{
		num1RealField.setText("");
		num1ImagField.setText("");
		num2RealField.setText("");
		num2ImagField.setText("");
	}
	
	public static void changeSelectedField(InputTextField newField)
	{
		selectedField = newField;
	}

}

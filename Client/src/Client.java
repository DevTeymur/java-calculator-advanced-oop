import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client {
	
	private static final int WIDTH = 520;
	private static final int HEIGHT = 660;
	private static final int port = 1224;
	private static final String hostInet = "localhost";
	
	public static ServerConnectionController connectionController;
	
	private static JFrame frame;
	
	private static InputTextField selectedField;
	
	private static InputTextField num1RealField;
	private static InputTextField num1ImagField;
	private static InputTextField num2RealField;
	private static InputTextField num2ImagField;
	
	private static JTextField outputField;

	public static void main(String[] args)
	{
		connectionController = new ServerConnectionController(hostInet, port);
		
		frame = new JFrame("Complex Number Calculator");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLayout(null);

		createConstLabels();
		
		num1RealField = new InputTextField(frame, 100, 20, 150, 40);
		num1ImagField = new InputTextField(frame, 290, 20, 150, 40);
		num2RealField = new InputTextField(frame, 100, 80, 150, 40);
		num2ImagField = new InputTextField(frame, 290, 80, 150, 40);
		
		selectedField = num1RealField;
		
		outputField = new JTextField();
		outputField.setBounds(30, 550, 460, 60);
		outputField.setFont(new Font("Calibre",Font.PLAIN,25));
		outputField.setEditable(false);
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
		
		frame.setLocationRelativeTo(null);
		
		if (!connectionController.connected)
			new ConnectionWindow(hostInet, port);
		else
			frame.setVisible(true);
	}
	
	public static void showFrame()
	{
		frame.setVisible(true);
	}

	public static void createConstLabels()
	{
		new Label(frame, "C1 = ", 35, 23, 60, 30);
		new Label(frame, "+", 260, 23, 30, 30);
		new Label(frame, "* i", 450, 15, 35, 60);

		new Label(frame, "C2 = ", 35, 83, 60, 30);
		new Label(frame, "+", 260, 83, 30, 30);
		new Label(frame, "* i", 450, 75, 35, 60);
	}

	public static void generateNumberKeyPad()
	{
		new NumberButton(frame, "1", 30, 230, 100, 60);
		new NumberButton(frame, "2", 150, 230, 100, 60);
		new NumberButton(frame, "3", 270, 230, 100, 60);

		new NumberButton(frame, "4", 30, 310, 100, 60);
		new NumberButton(frame, "5", 150, 310, 100, 60);
		new NumberButton(frame, "6", 270, 310, 100, 60);

		new NumberButton(frame, "7", 30, 390, 100, 60);
		new NumberButton(frame, "8", 150, 390, 100, 60);
		new NumberButton(frame, "9", 270, 390, 100, 60);
		
		new NumberButton(frame, "0", 150, 470, 100, 60);
		new NumberButton(frame, ".", 270, 470, 100, 60);
	}
	
	public static void generateOperatorKeyPad()
	{
		new OperationButton(frame, "DEL", 30, 150, 100, 60);		
		new OperationButton(frame, "+/-", 150, 150, 100, 60);		
		new OperationButton(frame, "C", 270, 150, 100, 60);
		new OperationButton(frame, "+", 390, 150, 100, 60);
		new OperationButton(frame, "-", 390, 230, 100, 60);
		new OperationButton(frame, "*", 390, 310, 100, 60);
		new OperationButton(frame, "/", 390, 390, 100, 60);
	}
	
	public static boolean isSelectedFieldEmpty()
	{
		return (
			selectedField.getText().equals("") || 
			selectedField.getText().equals("-"));
	}
	
	public static void setSelectedTextField(String text)
	{
		selectedField.setText(text);
	}
	
	public static String getSelectedTextField()
	{
		return selectedField.getText();
	}
	
	public static void calcAndShowResult(String operator)
	{
		String result = connectionController.calculate(
			num1RealField.getText(),
			num1ImagField.getText(),
			num2RealField.getText(),
			num2ImagField.getText(),
			operator
		);
		setOutPutText(result);
	}
	
	private static void setOutPutText(String text)
	{
		outputField.setText(text);
	}
	
	public static void clearFields()
	{
		num1RealField.setText("");
		num1ImagField.setText("");
		num2RealField.setText("");
		num2ImagField.setText("");
		outputField.setText("");
	}
	
	public static void backSpaceFiled()
	{
		if (!selectedField.getText().isEmpty())
		{	
			String newText = selectedField.getText().substring(
				0, selectedField.getText().length() -1
			);
			selectedField.setText(newText);
		}
	}
	
	public static void negate()
	{
		String oldText = selectedField.getText();
		if (oldText.startsWith("-"))
			selectedField.setText(oldText.substring(1));
		else
			selectedField.setText("-" + oldText);
	}
	
	public static void changeSelectedField(InputTextField newField)
	{
		selectedField = newField;
	}
}

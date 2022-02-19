import java.awt.event.*;

public class OBActionListener implements ActionListener{

	public OperationButton operationButton;
	
	public OBActionListener(OperationButton button)
	{
		operationButton = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (operationButton.getText().equals("C"))
		{
			clearCells();
		}
		else if (operationButton.getText().equals("="))
		{
			String result = Client.connectionController.calculate(
				Client.num1RealField.getText(),
				Client.num1ImagField.getText(),
				Client.num2RealField.getText(),
				Client.num2ImagField.getText(),
				Client.opLabel.getText()
			);
			Client.outputField.setText(result);
		}
		else
		{
			setOperation();
		}
		
	}
	
	public void clearCells()
	{
		Client.num1RealField.setText("");
		Client.num1ImagField.setText("");
		Client.num2RealField.setText("");
		Client.num2ImagField.setText("");

	}
	
	public void setOperation()
	{
		Client.opLabel.setText(operationButton.getText());
	}

}

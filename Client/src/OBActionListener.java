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
			// TODO: server calculation
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

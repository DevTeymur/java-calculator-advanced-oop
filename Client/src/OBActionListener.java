import java.awt.event.*;

public class OBActionListener implements ActionListener{

	private OperationButton operationButton;
	
	public OBActionListener(OperationButton button)
	{
		operationButton = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (operationButtonTextEquals("C"))
		{
			clearCells();
		}
		else if (operationButtonTextEquals("="))
		{
			String result = Client.findResult();
			Client.setOutPutText(result);
		}
		else
		{
			setOperation();
		}
		
	}
	
	private boolean operationButtonTextEquals(String text)
	{
		return operationButton.getText().equals(text);
	}
	
	private void clearCells()
	{
		Client.clearNumberFields();
	}
	
	private void setOperation()
	{
		Client.setOpLabelText(operationButton.getText());
	}

}

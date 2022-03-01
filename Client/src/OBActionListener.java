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
		else
		{
			String result = Client.findResult(getOperation());
			Client.setOutPutText(result);
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
	
	private String getOperation()
	{
		return operationButton.getText();
	}

}

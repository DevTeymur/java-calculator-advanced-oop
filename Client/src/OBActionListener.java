import java.awt.event.*;
import java.util.concurrent.CompletableFuture;

public class OBActionListener implements ActionListener{

	private final OperationButton operationButton;
	
	public OBActionListener(OperationButton button)
	{
		operationButton = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (operationButtonTextEquals("C"))
			Client.clearFields();
		else if (operationButtonTextEquals("DEL"))
			Client.backSpaceFiled();
		else if (operationButtonTextEquals("+/-"))
			Client.negate();
		else
			CompletableFuture.supplyAsync(()->Client.calcAndShowResult(getOperator()));
	}
	
	private boolean operationButtonTextEquals(String text)
	{
		return operationButton.getText().equals(text);
	}
	
	private String getOperator()
	{
		return operationButton.getText();
	}

}

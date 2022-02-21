import java.awt.event.*;

public class ITFMouseListener extends MouseAdapter
{
	public InputTextField inputTextField;
	
	public ITFMouseListener(InputTextField field)
	{
		inputTextField = field;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		Client.changeSelectedField(inputTextField);
		System.out.println("Cur Field contain: " + inputTextField.getText());
	}
}

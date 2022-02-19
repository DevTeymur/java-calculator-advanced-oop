import java.awt.event.*;

public class NBActionListener implements ActionListener{

	public NumberButton numberButton;
	 
	public NBActionListener(NumberButton button)
	{
		numberButton = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Client.selectedField.getText().equals("") && numberButton.getText().equals("."))
		{
			Client.selectedField.setText("0.");
		}
		else if ( ! 
			(
				Client.selectedField.getText().contains(".") && 
				numberButton.getText().equals(".")
			)
		)
		{
			String newText = Client.selectedField.getText() + numberButton.getText();
			Client.selectedField.setText(newText);
		}
	}
}

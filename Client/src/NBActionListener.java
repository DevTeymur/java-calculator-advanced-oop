import java.awt.event.*;

public class NBActionListener implements ActionListener{

	private NumberButton numberButton;
	 
	public NBActionListener(NumberButton button)
	{
		numberButton = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Client.isSelectedFieldEmpty() && buttonTextEquals("."))
		{
			Client.setSelectedTextField(Client.getSelectedTextField() + "0.");
		}
		else if ( ! 
			(
				Client.getSelectedTextField().contains(".") && 
				buttonTextEquals(".")
			)
		)
		{
			String newText = Client.getSelectedTextField() + numberButton.getText();
			Client.setSelectedTextField(newText);
		}
	}
	
	private boolean buttonTextEquals(String text)
	{
		return numberButton.getText().equals(text);
	}
}

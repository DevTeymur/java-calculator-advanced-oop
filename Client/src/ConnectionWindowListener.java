import java.awt.event.*;

public class ConnectionWindowListener extends WindowAdapter
{
	ConnectionWindow window;
	
	public ConnectionWindowListener(ConnectionWindow connectionWindow)
	{
		super();
		window = connectionWindow;
	}
	
	@Override
	public void windowClosing(WindowEvent evt)
	{
		if (window.isConnected())
		{
			window.dispose();
		}
		else
		{
			System.exit(0);
		}
	}
}

import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import javax.swing.*;

public class ConnectionWindow extends JFrame implements ActionListener
{
	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	private final JTextField inetAddressField;
	private final JTextField portField;
	
	public ConnectionWindow(String inetAddress, int port)
	{
		super("Connection error");
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(null);

		JLabel portLabel = new JLabel("Port", SwingConstants.CENTER);
		portLabel.setBounds(80, 0, 240, 50);
		portLabel.setFont(new Font("Calibre", Font.BOLD, 25));
		this.add(portLabel);

		portField = new JTextField(Integer.toString(port));
		portField.setBounds(120, 45, 160, 50);
		portField.setFont(new Font("Calibre",Font.PLAIN,30));
		this.add(portField);
		
		JLabel inetLabel = new JLabel("Inet Address", SwingConstants.CENTER);
		inetLabel.setBounds(80, 90, 240, 50);
		inetLabel.setFont(new Font("Calibre", Font.BOLD, 25));
		this.add(inetLabel);
		
		inetAddressField = new JTextField(inetAddress);
		inetAddressField.setBounds(50, 135, 300, 40);
		inetAddressField.setFont(new Font("Calibre",Font.PLAIN,30));
		this.add(inetAddressField);

		JButton tryAgainButton = new JButton("Try again");
		tryAgainButton.addActionListener(this);
		tryAgainButton.setBounds(100, 200, 200, 50);
		tryAgainButton.setBackground(Color.white);
		tryAgainButton.setFont(new Font("Calibre", Font.BOLD, 25));
		this.add(tryAgainButton);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public boolean isConnected()
	{
		return Client.connectionController.connected;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Client.connectionController.connectServer(
				inetAddressField.getText(), Integer.parseInt(portField.getText()));
		}
		catch (Exception exp)
		{
			exp.printStackTrace();
		}

		if (isConnected())
		{
			dispose();
			Client.showFrame();
		}
	}

}

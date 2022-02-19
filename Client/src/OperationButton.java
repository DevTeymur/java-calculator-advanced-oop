import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class OperationButton extends JButton
{
	private static final long serialVersionUID = 1L;
		
	public OperationButton(JFrame jframe, String text, int posX, int posY, int width, int height)
	{
		super(text);
		this.setBounds(posX, posY, width, height);
		this.setBackground(Color.white);
		this.setFont(new Font("Calibri", Font.BOLD, 25));
		this.addActionListener(new OBActionListener(this));
		jframe.add(this);
	}

}

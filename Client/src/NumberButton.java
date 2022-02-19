import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class NumberButton extends JButton
{
	private static final long serialVersionUID = 1L;
	
	public NumberButton(JFrame jframe, String text, int posX, int posY, int width, int height)
	{
		super(text);
		this.setBounds(posX, posY, width, height);
		this.setBackground(Color.white);
		this.setFont(new Font("Calibri", Font.BOLD, 25));
		this.addActionListener(new NBActionListener(this));
		jframe.add(this);
	}

}

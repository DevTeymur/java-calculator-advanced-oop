import java.awt.Color;
import java.awt.Font;
import java.io.Serial;

import javax.swing.*;

public class OperationButton extends JButton
{
	@Serial
	private static final long serialVersionUID = 1L;
		
	public OperationButton(JFrame jframe, String text, int posX, int posY, int width, int height)
	{
		super(text);
		this.setBounds(posX, posY, width, height);
		this.setBackground(Color.white);
		this.setFont(new Font("Calibre", Font.BOLD, 25));
		this.addActionListener(new OBActionListener(this));
		jframe.add(this);
	}

}

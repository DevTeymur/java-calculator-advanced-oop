import java.awt.Font;

import javax.swing.*;

public class Label extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	public Label(JFrame jframe, String text, int posX, int posY, int width, int height)
	{
		super(text);
		this.setBounds(posX, posY, width, height);  
	    this.setFont(new Font("Calibri", Font.PLAIN, 20));
	    this.setBackground(jframe.getBackground ());
	    this.setOpaque(true);
		jframe.add(this);
	}

}

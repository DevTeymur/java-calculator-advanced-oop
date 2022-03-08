import java.awt.Font;
import java.io.Serial;

import javax.swing.*;

public class Label extends JLabel
{
	@Serial
	private static final long serialVersionUID = 1L;
	
	public Label(JFrame jframe, String text, int posX, int posY, int width, int height)
	{
		super(text);
		this.setBounds(posX, posY, width, height);  
	    this.setFont(new Font("Calibre", Font.PLAIN, 20));
	    this.setBackground(jframe.getBackground ());
	    this.setOpaque(true);
		jframe.add(this);
	}

}

import java.awt.*;
import java.io.Serial;
import javax.swing.*;

public class InputTextField extends JTextField
{
	@Serial
	private static final long serialVersionUID = 1L;
	
	public InputTextField(JFrame jframe, int posX, int posY, int width, int height)
	{
		super();
		this.setBounds(posX, posY, width, height);
		this.setFont(new Font("Calibre",Font.PLAIN,30));
		this.addMouseListener(new ITFMouseListener(this));
	    jframe.add(this);  
	}

}

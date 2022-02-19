import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.NonWritableChannelException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Calc implements ActionListener {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 590;
	static JLabel opLabel;
	public static String op = "+";
	JFrame f;
	static JTextArea t1, t2, t3, t4, output;
	static JTextArea currentArea;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bAdd,bSub,bMul,bDiv,bDec,bEq,bC;

	Calc(){
		JFrame win = new JFrame("Complex Number Calculator");  // Create window
		win.setSize(WIDTH, HEIGHT);  // Set size
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setResizable(false);

		Calc.opLabel = new JLabel();
		Calc.opLabel.setBounds(30, 50, 20, 40);  
	    win.add(Calc.opLabel);
	    Calc.opLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
	    Calc.opLabel.setBackground(win.getBackground ());
	    Calc.opLabel.setOpaque(true);

	    win.setLayout(null);  
	
		createLabel(win, "C1 = ", 25, 25, 60, 30);
		createLabel(win, " + ", 245, 20, 40, 40);
		createLabel(win, "*", 440, 15, 10, 60);
		createLabel(win, " i ", 448, 10, 40, 60);
		
		createLabel(win, "C2 = ", 25, 70, 60, 60);
		createLabel(win, " + ", 245, 70, 40, 60);
		createLabel(win, "*", 440, 75, 10, 60);
		createLabel(win, " i ", 448, 70, 40, 60);
		
		JTextArea t1 = new JTextArea();  
	    textAreaProperties(win, t1, 85, 20, 150, 40);
	    
	    Calc.currentArea = t1;
	    
	    JTextArea t2 = new JTextArea();  
	    textAreaProperties(win, t2, 285, 20, 150, 40);
	   
	    JTextArea t3 = new JTextArea();  
	    textAreaProperties(win, t3, 85, 80, 150, 40);
	    
	    JTextArea t4 = new JTextArea();  
	    textAreaProperties(win, t4, 285, 80, 150, 40);
	    
	    
	    returnCurrentCell(t1);
	    returnCurrentCell(t2);
	    returnCurrentCell(t3);
	    returnCurrentCell(t4);
	    
	    JButton b1 = new JButton("1");
	    buttonProperites(win, b1, 30, 150, 90, 60);
	    setAction(b1);
	    
	    JButton b2 = new JButton("2");
	    buttonProperites(win, b2, 147, 150, 90, 60);
	    setAction(b2);

	    JButton b3 = new JButton("3");
	    buttonProperites(win, b3, 265, 150, 90, 60);
	    setAction(b3);
	    
	    JButton bC = new JButton("C");
	    buttonProperites(win, bC, 380, 150, 90, 60);
	    clearCell(bC);
	    
	    JButton b4 = new JButton("4");
	    buttonProperites(win, b4, 30, 230, 90, 60);
	    setAction(b4);
	    
	    JButton b5 = new JButton("5");
	    buttonProperites(win, b5, 147, 230, 90, 60);
	    setAction(b5);
	    
	    JButton b6 = new JButton("6");
	    buttonProperites(win, b6, 265, 230, 90, 60);
	    setAction(b6);

	    JButton bAdd = new JButton("+");
	    buttonProperites(win, bAdd, 380, 230, 90, 60);
	    setOperation(bAdd, win);
	    
	    JButton b7 = new JButton("7");
	    buttonProperites(win, b7, 30, 310, 90, 60);
	    setAction(b7);
	    
	    JButton b8 = new JButton("8");
	    buttonProperites(win, b8, 147, 310, 90, 60);
	    setAction(b8);
	    
	    JButton b9 = new JButton("9");
	    buttonProperites(win, b9, 265, 310, 90, 60);
	    setAction(b9);
	    
	    JButton bSub = new JButton("-");
	    buttonProperites(win, bSub, 380, 310, 90, 60);
	    setOperation(bSub, win);
	    
	    JButton b0 = new JButton("0");
	    buttonProperites(win, b0, 30, 390, 90, 60);
	    setAction(b0);
	   
	    JButton bDiv = new JButton("/");
	    buttonProperites(win, bDiv, 147, 390, 90,60);
	    setOperation(bDiv, win);
	    
	    JButton bMul = new JButton("x");
	    buttonProperites(win, bMul, 265, 390, 90, 60);
	    setOperation(bMul, win);
	    
	    JButton bDec = new JButton(".");
	    buttonProperites(win, bDec, 380, 390, 90, 60);
	    setAction(bDec);
	    
	    JButton bEq = new JButton("=");
	    buttonProperites(win, bEq, 30, 470, 90, 60);
	    
		win.setVisible(true);

	}
	
	

	public static void createLabel(JFrame win, String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, width, height);  
	    win.add(label);
	    label.setFont(new Font("Calibri", Font.PLAIN, 20));
	    label.setBackground(win.getBackground ());
	    label.setOpaque(true);

	    win.setLayout(null);  
	}
	
	public void textAreaProperties(JFrame win, JTextArea t, int x, int y, int width, int height) {
	    t.setBounds(x, y, width, height);  
        t.setFont(new Font("Calibri",Font.PLAIN,30));
	    win.add(t);  
	    win.setLayout(null); 
	}
	
	public void buttonProperites(JFrame win, JButton button, int x, int y, int width, int height) {
		button.setBounds(x, y, width, height);
		button.setBackground(Color.white);
		button.setFont(new Font("Calibri", Font.BOLD, 25));
		win.add(button);
		win.setLayout(null);
		
	}
	
	public void setAction(JButton b) {
		b.addActionListener(new ActionListener () {
	        public void actionPerformed (ActionEvent arg) {
	            String number=Calc.currentArea.getText()+b.getText();
	            Calc.currentArea.setText(number);
	        }
	    });
	}
	
	
	public void setOperation(JButton b, JFrame win) {
		b.addActionListener(new ActionListener () {
	        public void actionPerformed (ActionEvent arg) {
	            Calc.op = b.getText();
	            createLabel(win, op, 30, 50, 20, 40);
	        }
	    });
		 
	}
	
	public void clearCell(JButton b) {
		b.addActionListener(new ActionListener () {
	        public void actionPerformed (ActionEvent arg) {
	            Calc.currentArea.setText(null);
	            
	        }
	    });
	}

	
	public void returnCurrentCell(JTextArea t) {
	    t.addMouseListener(new MouseAdapter(){
	    	@Override
	    	public void mouseClicked(MouseEvent e){
	    		Calc.currentArea = t;
	    		System.out.println("Text field containing " + t.getText() + " clicked");
      		}
	    });
	} 
	
	public void equalButton(JButton b) {
		b.addActionListener(new ActionListener () {
	        public void actionPerformed (ActionEvent arg) {
	            
	        }
	    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args){
		new Calc();
		

	}


	

	
	
}
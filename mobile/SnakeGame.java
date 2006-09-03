package mobile;

/*
 * in a bold/lazy move this will include the drawing 
 * and logic of the game
 */

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

public class SnakeGame extends Canvas implements CommandListener {
	// interface and plumbing
	private Command exit;
    private DemoMIDlet parentMIDlet;
    private boolean alreadyDrawMap = false;
	public static final int KEY_UP = 12; // non-obscure clock mapping(tm)
	public static final int KEY_RIGHT = 3;
	public static final int KEY_DOWN = 6;
	public static final int KEY_LEFT = 9;
	// game parameters
	private int backgroundColor = 0x000000;    
	private int snakeColor = 0xffffff;
	private int wallColor = 0xff0000;
	private int W = getWidth();
	private int H = getHeight();
    // game values
    private int timer = 60;
    private boolean running = false;
    private int x = 0; // position of the snake
    private int y = 0;
    private int mx = 0; //movement, can be -1,0,1
    private int my = 1; // mx =1, my = 0 the snakes goes right.
    //debug crap
    private String s = "";
    
	public SnakeGame(DemoMIDlet parent) {
		super();
		//this.isDoubleBuffered();
		parentMIDlet = parent;
		exit = new Command("Exit", Command.BACK, 1);
		addCommand(exit);
		setCommandListener(this);
		//x = (Math.abs(rand.nextInt()) % (getWidth() -10)) + 5; 
		//y = (Math.abs(rand.nextInt()) % (getHeight() -10)) + 5; 
		running = true;
	}

	protected void paint(Graphics g) {
		if( false == alreadyDrawMap ){
			clearScreen(g);
			drawScores(g);
			alreadyDrawMap = true;
		}
		g.setColor(snakeColor);
		g.fillRect(x, y, 5, 5);
		x+= mx * 5;
		y+= my * 5;
		//repaint();
	}
	public void clearScreen(Graphics g){
		// paint it black
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight(  ));
		// raise walls
		g.setColor(wallColor);
		g.fillRect(0, 0, getWidth(), 5); // top
		g.fillRect(0, 0, 5, getHeight(  )); // left
		g.fillRect(getWidth()-5, 0, 5 , getHeight() ); //right
		g.fillRect(0, getHeight()-5, getWidth(), 5 ); //bottom
	}
	public void drawScores(Graphics g){
	      g.setColor(255, 255, 255);
	      g.drawString(timer + "", 0, 0, g.TOP | g.LEFT);
	}

	public void keyPressed(int keycode) {
		if( false == running ) return; 

		int game_key = 0;
		try{
        	game_key = getGameAction(keycode);
		} catch(Exception e) {
        	game_key = 0;
        }
		int key = keycode;
		int new_key = 0;
		
		if (game_key == Canvas.LEFT || key == Canvas.KEY_NUM4) {
    		//new_key = KEY_LEFT;
			mx = -1; my = 0;
		} else if (game_key == Canvas.RIGHT || key == Canvas.KEY_NUM6) {
        	//new_key = KEY_RIGHT;
			mx = 1; my = 0;
        } else if (game_key == Canvas.UP || key == Canvas.KEY_NUM2) {
        	//new_key = KEY_UP;
        	mx = 0; my = -1;
        } else if (game_key == Canvas.DOWN || key == Canvas.KEY_NUM8) {
        	//new_key = KEY_DOWN;
        	mx = 0; my = 1;
        }
		repaint();
	}
	public void commandAction(Command cmd, Displayable displayable) {
		s = cmd.getLabel();
		if( cmd == exit ){
			quit();
		}
		repaint();
	}

	
	public void resume(){
	}
	public void pause(){
	}
	public void quit(){
		//display.setCurrent(menuCanvas);
		// TODO: how the hell do you go back to the menu?
		//parentMIDlet.notifyDestroyed();
		parentMIDlet.drawMenu();
	}
	public static final void debug( String s ) {
		System.out.println( s );
	}


}

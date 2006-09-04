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
import javax.microedition.lcdui.Image;

public class SnakeGame extends Canvas implements CommandListener {
	// interface and plumbing
	private Command exit;
    private DemoMIDlet parentMIDlet;
    private boolean alreadyDrawMap = false;
	public static final int KEY_UP = 12; // non-obscure clock mapping(tm)
	public static final int KEY_RIGHT = 3;
	public static final int KEY_DOWN = 6;
	public static final int KEY_LEFT = 9;
	private Image i; // this one is tricky. the Canvas cannot read the damn colors it has, so we will
	                          //simulate a doublebuffer update. basicaly, it will be our hash table with itens positions
	                         //and we will check it for colisions, update the image, and print it on the lame canvas
	private Graphics g;
	private long lastTick = 0;
	private int tickInterval = 1000;
	// game parameters
	private int backgroundColor = 0xFF000000;
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
    
	public SnakeGame(DemoMIDlet parent) {
		super();
		//this.isDoubleBuffered();
		parentMIDlet = parent;
		exit = new Command("Exit", Command.BACK, 1);
		addCommand(exit);
		setCommandListener(this);
		//x = (Math.abs(rand.nextInt()) % (getWidth() -10)) + 5; 
		//y = (Math.abs(rand.nextInt()) % (getHeight() -10)) + 5;
		x = 10; y = 10; mx = 1; my = 0;
		// make our fake image
		i = Image.createImage(W,H);
		g = i.getGraphics();
		// start
		running = true;
		loop();
	}
	private synchronized void loop () {
		// only run at every seccond
		//if( System.currentTimeMillis() - lastTick > tickInterval ){
			//lastTick = System.currentTimeMillis();

			// calculate new position
			x+= mx * 5;
			y+= my * 5;
			// check for colision
			int[] step = new int[5 * 5];
			i.getRGB( step, 0, 5, 0, 0, 5, 5);
			for( int index = 0; index < step.length; index++){
				if( step[index] != backgroundColor ){
					// DEAD never got this comparrision to work
					this.setTitle( i.getGraphics().getDisplayColor(step[index]) + " " + i.getGraphics().getDisplayColor(backgroundColor));
				}
			}
			 // draw
		//}
		//loop();
	}
	protected void paint(Graphics gMain) {
		if( false == alreadyDrawMap ){
			clearScreen(gMain);
			drawScores(gMain);
			alreadyDrawMap = true;
		}
		g.setColor(snakeColor);
		g.fillRect(x, y, 5, 5);
		gMain.drawImage(i, 0, 0, 0);
	}
	public void clearScreen(Graphics gOld){
		// paint it black
		g.setColor(backgroundColor);
		g.fillRect(0, 0, W, H);
		// raise walls
		g.setColor(wallColor);
		g.fillRect(0, 0, W, 5); // top
		g.fillRect(0, 0, 5, H); // left
		g.fillRect(W-5, 0, 5 , H ); //right
		g.fillRect(0, H-5, W, 5 ); //bottom
		gOld.drawImage(i, 0, 0, 0);
	}
	public void drawScores(Graphics g){
	      g.setColor(255, 255, 255);
	      g.drawString(timer + "", 0, 0, Graphics.TOP | Graphics.LEFT);
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
		//int new_key = 0;
		
		if (game_key == Canvas.LEFT || key == Canvas.KEY_NUM4) {
    		//new_key = KEY_LEFT;
			mx = -1; my = 0;
		}else if (game_key == Canvas.RIGHT || key == Canvas.KEY_NUM6) {
        	//new_key = KEY_RIGHT;
			mx = 1; my = 0;
        }else if (game_key == Canvas.UP || key == Canvas.KEY_NUM2) {
        	//new_key = KEY_UP;
        	mx = 0; my = -1;
        }else if (game_key == Canvas.DOWN || key == Canvas.KEY_NUM8) {
        	//new_key = KEY_DOWN;
        	mx = 0; my = 1;
        }
		loop();
	}
	public void commandAction(Command cmd, Displayable displayable) {
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

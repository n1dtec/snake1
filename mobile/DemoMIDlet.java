package mobile;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;

public class DemoMIDlet extends MIDlet implements CommandListener {
	private boolean paused = false; // see comments in the constructor
	//private Command exit; // commands for the default...
	//private Command ok;  // ...side buttons.
	//private Alert about;
	private List menuList;
	
	public DemoMIDlet() {
		super();
		if(paused) { // this is true if the user exited the game to use the phone and came back
			//TODO game.resumeGame();
			return; // don't use multiple exit points. do as i say no as i do
		}
		

		// the menu screen
		menuList = new List("Snake One", Choice.IMPLICIT);
		try{
			Image img = Image.createImage("/res/game.png");
			menuList.append("Start Game",img);
		}catch(Exception e){
			menuList.append("Start Game",null);
		}
		menuList.append("High Scores",null);
		menuList.append("About",null);
		menuList.append("Quit",null);
		//menuScreen.setCommandListener(this);
		// side buttons
		//exit = new Command("Exit", Command.EXIT, 1);
		//ok = new Command("About", Command.OK, 1);
		//menuScreen.addCommand(exit);
		//menuScreen.addCommand(ok);
	}

	protected void startApp() throws MIDletStateChangeException {
		//Display.getDisplay(this).setCurrent(menuScreen);
		Display display = Display.getDisplay(this);
		display.setCurrent(menuList);
		//TODO mostrar high scores se demorar para escolher
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	public void commandAction(Command cmd, Displayable displayable) {
		/*if( cmd == exit ){
			//TODO game.quit();
			destroyApp(true);
			notifyDestroyed();
		}*/
		
		String label = cmd.getLabel();

		if("EXIT".equals(label))
			notifyDestroyed();
		else if("HELP".equals(label))
		  //displayHelp();
			notifyDestroyed();
		else if("OK".equals(label))
		  //processForm();
			notifyDestroyed();
	}

}

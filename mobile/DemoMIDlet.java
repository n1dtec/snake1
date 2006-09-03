package mobile;

import javax.microedition.lcdui.AlertType;
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
	private Command exit; // commands for the default...
	private Command menuOk;  // ...side buttons.
	private Alert dialog;
	private List menuList;
	private Image logo;
	
	public DemoMIDlet() {
		super();
		if(paused) { // this is true if the user exited the game to use the phone and came back
			//TODO game.resumeGame();
			return; // don't use multiple exit points. do as i say no as i do
		}
		try{
			logo = Image.createImage("/res/game.png");
		}catch(Exception e){quit();}

		// the menu screen
		menuList = new List("Snake One", Choice.IMPLICIT);
		menuList.append("Start Game",null); // 0
		menuList.append("High Scores",null); // 1
		menuList.append("Help",null); // 2
		menuList.append("About",null); // 2
		menuList.append("Quit",null); // 3
		menuList.setCommandListener(this);
		// side buttons
		exit = new Command("Exit", Command.EXIT, 1);
		menuOk = new Command("OK", Command.OK, 2);
		menuList.addCommand(exit);
		menuList.addCommand(menuOk);
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

		if("Exit".equals(label)){
			quit();
		}else if("OK".equals(label)){
			int option =  menuList.getSelectedIndex();
			if( 0 == option ){
				//startGame();
			}else if( 1 == option ){
				displayHighscores();
			}else if( 2 == option ){
				displayHelp();
			}else if( 3 == option ){
				displayAbout();
			}else if( 4 == option ){
				quit();
			}
		}
	}

	public void debug(String s){
		dialog = new Alert("DEBUG", s, null, AlertType.INFO);
		dialog.setString(s);
		dialog.setTimeout(Alert.FOREVER);
		Display display = Display.getDisplay(this);
		display.setCurrent(dialog);
	}
	public void displayAbout() {
		dialog = new Alert("About");
		dialog.setImage(logo);
		dialog.setString("The very first snake game.\nCoded to your cellphone by Gabriel Barros");
		dialog.setType(AlertType.INFO);
		dialog.setTimeout(Alert.FOREVER);
		Display display = Display.getDisplay(this);
		display.setCurrent(dialog);
	}
	public void displayHelp() {
		dialog = new Alert("Help");
		dialog.setImage(logo);
		dialog.setString("You are the snake.\n You start in a random position in the screen, facing a random direction.\n move without hitting the walls or your own body.");
		dialog.setType(AlertType.INFO);
		dialog.setTimeout(Alert.FOREVER);
		Display display = Display.getDisplay(this);
		display.setCurrent(dialog);
	}
	public void displayHighscores() {
		dialog = new Alert("High Score");
		dialog.setImage(logo);
		dialog.setString("503 not implemented");
		dialog.setType(AlertType.INFO);
		dialog.setTimeout(Alert.FOREVER);
		Display display = Display.getDisplay(this);
		display.setCurrent(dialog);
	}
	
	public void quit(){
		notifyDestroyed();
	}
}

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
	//private Command menuOk;  // ...side buttons.
	private Alert dialog;
	private List menuList;
	private Image logo;
	final private SnakeGame game = new SnakeGame();
	

	protected void startApp() throws MIDletStateChangeException {
		if(paused) { // this is true if the user exited the game to use the phone and came back
			game.resume();
		}else{
			// load logo for the dialogs
			try{
				logo = Image.createImage("/res/game.png");
			}catch(Exception e){quit();}
			// the menu list
			menuList = new List("Snake One", Choice.IMPLICIT);
			menuList.append("Start Game",null); // 0
			menuList.append("High Scores",null); // 1
			menuList.append("Help",null); // 2
			menuList.append("About",null); // 2
			menuList.append("Quit",null); // 3
			menuList.setCommandListener(this);
			// side buttons
			exit = new Command("Exit", Command.EXIT, 1); menuList.addCommand(exit);
			//menuOk = new Command("OK", Command.ITEM, 2); menuList.addCommand(menuOk);
			Display display = Display.getDisplay(this);
			display.setCurrent(menuList);
			//TODO mostrar high scores se demorar para escolher
		}
	}

	protected void pauseApp() {
		game.pause();
		paused = true;
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		Display.getDisplay(this).setCurrent(null);
	}

	
	public void commandAction(Command cmd, Displayable displayable) {
		String label = cmd.getLabel();
		if("Exit".equals(label)){
			quit();
		}else{ //else if("OK".equals(label)){ #any other button will do... don't know how to set label in the middle one...
			int option =  menuList.getSelectedIndex();
			if( 0 == option ){
				Display.getDisplay(this).setCurrent( game );
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
		dialog.setString("The very first snake game.\n\nCoded to your cellphone by Gabriel Barros");
		dialog.setType(AlertType.INFO);
		dialog.setTimeout(Alert.FOREVER);
		Display display = Display.getDisplay(this);
		display.setCurrent(dialog);
	}
	public void displayHelp() {
		dialog = new Alert("Help");
		dialog.setImage(logo);
		dialog.setString("You are the snake.\n\nYou start in a random position in the screen, facing a random direction.\n\nmove without hitting the walls or your own body.\n\nSurvive until the timer reaches zero.");
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

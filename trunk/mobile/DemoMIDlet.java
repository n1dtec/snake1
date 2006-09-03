package mobile;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.TextBox

public class DemoMIDlet extends MIDlet implements CommandListener {
	private Command exit; // commands for the...
	private Command ok;  // ...side buttons.
	private Alert about;

	public DemoMIDlet() {
		super();
		// about dialog
		about = new Alert("About");
		about.setString("Created by gabriel.barros@gmail.com");
	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		Display.getDisplay(this).setCurrent();
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub

	}

}

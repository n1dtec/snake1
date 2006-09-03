package mobile;

/*import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class MenuScreen extends Canvas {

	public MenuScreen() {
		super();
		// TODO Auto-generated constructor stub
		// TODO the objective in this class is to make a menu, and to select
		// the option with the arrows
	}

	protected void paint(Graphics arg0) {
		// TODO Auto-generated method stub

	}

}
*/

import javax.microedition.lcdui.*;

public class MenuScreen extends Canvas {
   public void paint(Graphics g) {
      g.setColor(255, 0, 0);
      g.fillRect(0, 0, getWidth(), getHeight(  ));
      g.setColor(255, 255, 255);
      g.drawString("Hello World!", 0, 0, g.TOP | g.LEFT);
   }
}

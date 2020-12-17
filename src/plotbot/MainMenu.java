package plotbot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

/**
 * This class implements the robot interface for the user
 * @author bp
 *
 */
public class MainMenu {

	private static final String[] ITEMS = { "Anchor", "Diamond in Rec" };
	private static final String TITLE = "Choose Shape to draw:";
	private TextMenu menu;
	private double width; // width of the selected shape will be stored
	private double height; // height of the selected shape will be stored
	
	/**
	 * The constructor creates a new text menu object to use as interface with
	 * the NXT brick
	 */
	public MainMenu() {
		this.menu = new TextMenu(ITEMS, 1, TITLE);
		LCD.drawString("Enter-Choose", 0, 6);

	}

	/**
	 * This method returns a plottable object selected by the user
	 * from the text menu interface
	 * @return Plottable an object of the type Plottable
	 * @throws Exception
	 */
	public Plottable select() throws Exception {
		int selection = -1;
		do {
			selection = menu.select();
		} while (selection < 0);

		while (Button.ENTER.isDown()) {
		}
		Plottable toDraw = null;
		if (selection == 0) {
			toDraw = new Anchor();
			toDraw.isSelectedToDraw();
			anchorInterface();
		} else if (selection == 1) {
			toDraw = new DiamondInRectangle();
			toDraw.isSelectedToDraw();
			diaInRectInterface();
		}
		return toDraw;
	}

	/**
	 * This method implements an interactive interface to enter
	 * the details of the anchor shape by the user
	 * @throws Exception
	 */
	public void anchorInterface() throws Exception {
		LCD.clear();
		LCD.drawString("For the Anchor", 0, 0);
		LCD.drawString("Enter the width", 0, 2);
		LCD.drawString("Press RIGHT", 0, 6);
		LCD.drawString("to continue", 0, 7);
		Button.RIGHT.waitForPressAndRelease();
		width = takeUserInput("Width");
		checkForValidInput(width);
		LCD.clear();
		LCD.drawString("Entered width ", 0, 0);
		LCD.drawInt((int) width, 0, 1);
		LCD.drawString("ESCAPE:Menu", 0, 6);
		LCD.drawString("RIGHT:Cont..", 0, 7);
		while (Button.ESCAPE.isUp() && Button.RIGHT.isUp()) {

		}
		if (Button.ESCAPE.isDown()) {
			Button.ESCAPE.waitForPressAndRelease();
			throw new Exception(); // returns to main menu if the user wants to
									// exit
		}
		Button.RIGHT.waitForPressAndRelease();
		LCD.clear();
	}

	/**
	 * This method implements an interactive interface to enter
	 * the details of the diamond in a rectangle shape by the user
	 * @throws Exception
	 */
	public void diaInRectInterface() throws Exception {
		LCD.clear();
		LCD.drawString("For the Diamond", 0, 0);
		LCD.drawString("inside a ", 0, 1);
		LCD.drawString("rectangle", 0, 2);
		LCD.drawString("Enter the width", 0, 3);
		LCD.drawString("Press RIGHT", 0, 6);
		LCD.drawString("to continue", 0, 7);
		Button.RIGHT.waitForPressAndRelease();
		width = takeUserInput("Width");
		checkForValidInput(width);
		LCD.clear();
		LCD.drawString("For the Diamond", 0, 0);
		LCD.drawString("inside a ", 0, 1);
		LCD.drawString("rectangle", 0, 2);
		LCD.drawString("Enter the height", 0, 3);
		LCD.drawString("Press RIGHT", 0, 6);
		LCD.drawString("to continue", 0, 7);
		Button.RIGHT.waitForPressAndRelease();
		height = takeUserInput("Height");
		checkForValidInput(height);
		LCD.clear();
		LCD.drawString("Entered width ", 0, 0);
		LCD.drawInt((int) width, 0, 1);
		LCD.drawString("Entered height ", 0, 2);
		LCD.drawInt((int) height, 0, 3);
		LCD.drawString("ESCAPE:Menu", 0, 6);
		LCD.drawString("RIGHT:Cont..", 0, 7);
		while (Button.ESCAPE.isUp() && Button.RIGHT.isUp()) {

		}
		if (Button.ESCAPE.isDown()) {
			Button.ESCAPE.waitForPressAndRelease();
			throw new Exception();// returns to main menu if the user wants to
									// exit
		}
		Button.RIGHT.waitForPressAndRelease();
		LCD.clear();
	}

	/**
	 * This method returns the height of the shape object
	 * @return height of the shape object (in mm)
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * This method returns the width of the shape object
	 * @return width of the shape object (in mm)
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * This method allows the user to choose width and height interactively
	 * from the LCD screen of the leJOS NXT brick.
	 * @param name the name of the dimension whose value is to be selected 
	 * @return selected value as integer 
	 */
	public int takeUserInput(String name) {
		int x = 0;
		while (!Button.ENTER.isDown()) {
			if (Button.LEFT.isDown()) {
				userInputDisplay();
				Button.LEFT.waitForPressAndRelease();
				x -= 5; // increment value for selection
				LCD.drawString(name + ":" + x, 0, 2);
			} else if (Button.RIGHT.isDown()) {
				userInputDisplay();
				Button.RIGHT.waitForPressAndRelease();
				x += 5; // increment value for selection
				LCD.drawString(name + ":" + x, 0, 2);
			}
		}
		return x;
	}

	/**
	 * This method is used to check whether the user has selected a valid dimensions
	 * for the shape object.
	 * @param value dimension for a shape
	 * @throws Exception
	 */
	public void checkForValidInput(double value) throws Exception {
		if (value <= 0) {
			LCD.clear();
			LCD.drawString("Invalid I/P val", 0, 0);
			LCD.drawString("To main menu", 0, 2);
			LCD.drawString("Press Escape", 0, 3);
			while (!Button.ESCAPE.isDown()) {
			}
			throw new Exception();
		}
	}

	/**
	 * This method is used to display instructions to the user on the
	 * leJOS NXT brick LCD display.
	 */
	public void userInputDisplay() {
		LCD.clear();
		LCD.drawString("Press Left --", 0, 0);
		LCD.drawString("Press Right ++", 0, 1);
		LCD.drawString("Enter->confirm", 0, 7);
	}
}
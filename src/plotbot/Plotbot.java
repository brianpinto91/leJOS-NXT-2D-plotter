package plotbot;

import lejos.nxt.Button;
import lejos.nxt.LCD;

/**
 * This class contains the entrypoint for the plotting robot
 * @author Brian
 *
 */
public class Plotbot {

	private static MainMenu myMainMenu;
	private static Plottable s;
	public static Calibration cb;
	private static boolean result;
	public static RoboParameters para = new RoboParameters();

	// entry point of the program
	public static void main(String[] args) throws Exception {
		LCD.drawString("Compiled successfully", 0, 0);
		LCD.drawString("Press Escape", 0, 1);
		Button.ESCAPE.waitForPressAndRelease();
		run();
	}
	
	/**
	 * runs sequence of tasks from selecting of shape to calibration and then drawing of the selected
	 * shape and repeats these steps until the robot is forcefully shutdown.
	 * @throws Exception
	 */
	public static void run() throws Exception {
		LCD.clear();
		result = false;
		while (!result) {
			goToMainMenu();
			LCD.clear();
			LCD.drawString("Place Robot", 0, 0);
			LCD.drawString("ahead of black", 0, 1);
			LCD.drawString("bar and", 0, 2);
			LCD.drawString("Robo Y axis", 0, 3);
			LCD.drawString("matching", 0, 4);
			LCD.drawString("board Y axis", 0, 5);
			LCD.drawString("Enter-Cont", 0, 7);
			Button.ENTER.waitForPressAndRelease();
			LCD.clear();
			LCD.drawString("Calibrating..", 0, 0);
			cb = new Calibration();
			LCD.clear();
			LCD.drawString("Calibration completed", 0, 0);
			checkIfDrawable();
		}
		plot();
	}
	
	/**
	 * This method starts plotting the selected shape and once completed returns to origin.
	 * @throws Exception
	 */
	public static void plot() throws Exception {
		PlotbotControl pc = new PlotbotControl();
		LCD.clear();
		LCD.drawString("Drawing....", 0, 0);
		if (s.getStatus().equals("yesDrawDiamondInRectangle")) {
			DiamondInRectangle dia = new DiamondInRectangle();
			dia.setWidth(myMainMenu.getWidth());
			dia.setHeight(myMainMenu.getHeight());
			dia.plot(pc);
			pc.MoveToXY(0, 0);
		} else if (s.getStatus().equals("yesDrawAnchor")) {
			Anchor anc = new Anchor();
			anc.setWidthHeight(myMainMenu.getWidth());
			anc.plot(pc);
			pc.MoveToXY(0, 0);
		}
		LCD.clear();
		LCD.drawString("Drawing Compl", 0, 0);
		LCD.drawString("ESCAPE:Menu", 0, 6);
		Button.ESCAPE.waitForPressAndRelease();
		LCD.clear();
		run(); // go back to home menu
	}

	/**
	 * This method displays the main menu and if there is an exception then
	 * it again returns to main menu by calling the run() method
	 * @throws Exception
	 */
	public static void goToMainMenu() throws Exception {
		try {
			myMainMenu = new MainMenu();
			s = myMainMenu.select();
		} catch (Exception e) {
			run();
		}
	}
	
	/**
	 * This method checks whether the entered dimensions of the shape are within the boundary limits.
	 * max width is derived from the maximum swivel angle determined from calibration.
	 */
	public static void checkIfDrawable() {
		if (myMainMenu.getWidth() >= para.getMaxWidth() || myMainMenu.getHeight() >= para.getMaxHeight()) {
			LCD.drawString("Large Value", 0, 0);
			LCD.drawString("Cannot draw", 0, 0);
			LCD.drawString("Escape:Menu", 0, 6);
			Button.ESCAPE.waitForPressAndRelease();
			result = false;
		} else {
			result = true;
		}
	}
}
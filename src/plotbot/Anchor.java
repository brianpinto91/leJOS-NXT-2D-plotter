package plotbot;

/**
 * Class representing a plottable anchor.
 * @author bp
 *
 */
public class Anchor implements Plottable {

	private double width;
	private double height;
	private double HWratio=2;
	public double yStart = 230;
	private Line line = new Line();
	private PlotbotControl roboControl;
	private String status;
	
	/**
	 * This method plots a anchor by using the PlotbotControl object.
	 * @param PlotbotControl A object of the type PlotbotControl
	 */
	public void plot(PlotbotControl pc) {
		this.roboControl = pc;
		drawAnchor();

	}
	
	/**
	 * This method draws a anchor defined by its width and height.
	 * From the given width, the other dimensions of the anchor are derived.
	 * The proportions used: Side arms for anchor = 0.1*Height;
	 * Position of the Diamond from top is at 0.7*Height;
	 * The central arm of the anchor at 0.5*Height;
	 * The width of the central arm = 0.5*width;
	 * The width of the diamond = 0.5*width;
	 * The points of the direction change are identified and the backlash is compensated
	 */
	public void drawAnchor() {
		roboControl.prepDirecChangeCCW();
		line.setCoord(-width / 2, yStart - 0.1 * height, -width / 2, yStart);
		roboControl.MoveToXY(-width / 2, yStart - 0.1 * height);
		roboControl.penDown();
		line.plot(roboControl);
		roboControl.prepDirecChangeCW();
		line.setCoord(-width / 2, yStart, width / 2, yStart);
		line.plot(roboControl);
		line.setCoord(width / 2, yStart, width / 2, yStart - 0.1 * height);
		line.plot(roboControl);
		roboControl.penUp();
		roboControl.prepDirecChangeCCW();
		line.setCoord(0, yStart, 0, yStart - 0.7 * height);
		roboControl.MoveToXY(0, yStart);
		roboControl.penDown();
		line.plot(roboControl);
		roboControl.penUp();
		roboControl.MoveToXY(-0.25 * width, yStart - 0.5 * height);
		roboControl.prepDirecChangeCW();
		line.setCoord(-0.25 * width, yStart - 0.5 * height, 0.25 * width , yStart - 0.5 * height);
		roboControl.penDown();
		line.plot(roboControl);
		roboControl.penUp();
		roboControl.prepDirecChangeCCW();
		roboControl.MoveToXY(-0.25 * width, yStart - 0.85 * height);
		Diamond diamond = new Diamond();
		diamond.setCoord(0, yStart - 0.7 * height, 0.25 * width, yStart - 0.85 * height);
		roboControl.penDown();
		diamond.plot(roboControl);
		roboControl.penUp();
	}

	/**
	 * This method is used to set the width and height for the anchor to be drawn.
	 * The height is derived from the specified width parameter
	 * @param width required anchor width (in mm)
	 */
	public void setWidthHeight(double width) {
		this.width = width;
		defineHeight();
	}

	/**
	 * This method is used to set the height for the anchor to be drawn.
	 * The height is derived from the specified width parameter and aspect ratio.
	 */
	public void defineHeight() {
		this.height = this.HWratio * this.width;
	}
	
	/**
	 * This method sets the status whether to draw a anchor or not
	 */
	public void isSelectedToDraw() {
		this.status = "yesDrawAnchor";
	}
	
	/**
	 * This method return the status whether to draw an anchor or not
	 */
	public String getStatus() {
		return this.status;
	}

}
package plotbot;

/**
 * Class representing a plottable rectangle.
 * @author bp
 *
 */
public class Rectangle implements Plottable {
	
	private String status;
	private double lowLeftX;
	private double lowLeftY;
	private double upRightX;
	private double upRightY;
	private PlotbotControl roboControl;
	private Line line = new Line();
	
	/**
	 * This method sets the parameters of the rectangle to be drawn.
	 * A plottable rectangle is defined by Lower left X, Lower left Y, Upper right X, Upper right Y.
	 * @param lowleftX the lower left X coordinate
	 * @param lowleftY the lower left Y coordinate
	 * @param uprightX the upper right X coordinate
	 * @param uprightY the upper right Y coordinate
	 */
	public void setCoord(double lowleftX, double lowleftY, double uprightX, double uprightY) {
		this.lowLeftX = lowleftX;
		this.lowLeftY = lowleftY;
		this.upRightX = uprightX;
		this.upRightY = uprightY;
	}
	
	/**
	 * This method plots a rectangle by using the PlotbotControl object.
	 * @param PlotbotControl A object of the type PlotbotControl
	 */
	public void plot(PlotbotControl pc) {
		roboControl = pc;
		drawRect();
	}
	
	/**
	 * This method draws a rectangle by drawing lines sequentially
	 * starting from the lower left corner of the rectangle.
	 */ 
	public void drawRect() {
		this.roboControl.prepDirecChangeCCW();
		this.line.setCoord(lowLeftX, lowLeftY, -upRightX, upRightY);
		this.roboControl.MoveToXY(lowLeftX,lowLeftY);
		this.roboControl.penDown();
		this.line.plot(roboControl);
		this.roboControl.prepDirecChangeCW();
		this.line.setCoord(-upRightX, upRightY, upRightX , upRightY);
		this.line.plot(roboControl);
		this.line.setCoord(upRightX, upRightY, upRightX, lowLeftY);
		this.line.plot(roboControl);
		this.roboControl.prepDirecChangeCCW();
		this.line.setCoord(upRightX, lowLeftY, lowLeftX , lowLeftY);
		this.line.plot(roboControl);
		this.roboControl.penUp();
	}
	
	/**
	 * This method sets the status whether to draw a rectangle or not
	 */
	public void isSelectedToDraw(){
		this.status="yesDrawRectangle";
	}
	
	/**
	 * This method return the status whether to draw a rectangle or not
	 */
	public String getStatus(){
		return this.status;
	}
}
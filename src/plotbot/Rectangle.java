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
	 * @param X1 the start x coordinate
	 * @param Y1 the start y coordinate
	 * @param X2 the end x coordinate
	 * @param Y2 the end y coordinate
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
		roboControl.prepDirecChangeCCW();
		line.setCoord(lowLeftX, lowLeftY, -upRightX, upRightY);
		roboControl.MoveToXY(lowLeftX,lowLeftY);
		roboControl.penDown();
		line.plot(roboControl);
		roboControl.prepDirecChangeCW();
		line.setCoord(-upRightX, upRightY, upRightX , upRightY);
		line.plot(roboControl);
		line.setCoord(upRightX, upRightY, upRightX, lowLeftY);
		line.plot(roboControl);
		roboControl.prepDirecChangeCCW();
		line.setCoord(upRightX, lowLeftY, lowLeftX , lowLeftY);
		line.plot(roboControl);
		roboControl.penUp();
	}
	
	/**
	 * This method sets the status whether to draw a rectangle or not
	 */
	public void isSelectedToDraw(){
		status="yesDrawRectangle";
	}
	
	/**
	 * This method return the status whether to draw a rectangle or not
	 */
	public String getStatus(){
		return status;
	}
}
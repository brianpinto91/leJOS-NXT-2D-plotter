package plotbot;

/**
 * Class representing a plottable rectangle.
 * @author bp
 *
 */
public class Diamond implements Plottable {
	private double upX;
	private double upY;
	private double rightX;
	private double rightY;
	private PlotbotControl roboControl;
	private Line line = new Line();
	private String status;

	/**
	 * This method sets the parameters of the rectangle to be drawn.
	 * A plottable diamond is defined by Lower upper X, upper Y, right X, right Y.
	 * @param upX the upper X coordinate
	 * @param upY the upper Y coordinate
	 * @param rightX the right X coordinate
	 * @param rightY the right Y coordinate
	 */
	public void setCoord(double upX, double upY, double rightX, double rightY) {
		this.upX = upX;
		this.upY = upY;
		this.rightX = rightX;
		this.rightY = rightY;
	}

	public void plot(PlotbotControl pc) {
		this.roboControl = pc;
		drawDia();
	}

	/**
	 * This method draws a diamond by drawing lines sequentially
	 * starting from the left corner of the diamond.
	 */  
	public void drawDia() {
		this.roboControl.prepDirecChangeCW();
		this.line.setCoord(-rightX, rightY, upX, upY);
		this.roboControl.MoveToXY(-rightX, rightY);
		this.roboControl.penDown();
		this.line.plot(roboControl);
		this.line.setCoord(upX, upY, rightX, rightY);
		this.line.plot(roboControl);
		this.roboControl.prepDirecChangeCCW();
		this.line.setCoord(rightX, rightY,upX, 2*rightY-upY);
		this.line.plot(roboControl);
		this.line.setCoord(upX, 2*rightY-upY, -rightX, rightY);
		this.line.plot(roboControl);
		this.roboControl.penUp();
	}
	
	/**
	 * This method sets the status whether to draw a diamond or not
	 */
	public void isSelectedToDraw(){
		status="yesDrawDiamond";
	}
	
	/**
	 * This method return the status whether to draw a diamond or not
	 */
	public String getStatus(){
		return status;
	}
}
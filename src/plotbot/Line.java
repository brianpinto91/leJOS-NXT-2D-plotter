package plotbot;

/**
 * Class representing a plottable line.
 * @author bp
 *
 */
public class Line implements Plottable {
	public double x1;
	public double x2;
	public double y1;
	public double y2;
	public double lineCoarse=1; //how fine is the inclined/horizontal line depends on this value
	PlotbotControl roboControl;
	private String status;
	
	/**
	 * This method plots a line by using the PlotbotControl object.
	 * @param PlotbotControl A object of the type PlotbotControl
	 */
	public void plot(PlotbotControl pc) {
		this.roboControl = pc;
		drawLine();
	}
	
	/**
	 * This method sets the start and end points for the line to be drawn.
	 * @param X1 the start x coordinate
	 * @param Y1 the start y coordinate
	 * @param X2 the end x coordinate
	 * @param Y2 the end y coordinate
	 */
	public void setCoord(double X1, double Y1, double X2, double Y2){
		this.x1=X1;
		this.y1=Y1;
		this.x2=X2;
		this.y2=Y2;
	}

	/**
	 * This method identifies if the line is Horizontal or Vertical or Inclined
	 */
	public void drawLine() {
		roboControl.MoveToXY(x1, y1);
		if (x1 == x2) {
			drawVerticalLine();
		} else if (y1 == y2) {
			drawHorizontalLine();
		} else {
			drawInclinedLine();
		}
	}
	
	/**
	 * This method draws a vertical line
	 */
	public void drawVerticalLine() {
		roboControl.MoveToXY(this.x2, this.y2);
	}
	
	/**
	 * This method draws a horizontal line. The direction is first identified that is towards right or left.
	 * Then incrementally move (because the robot uses swivel arm to reach horizontal
	 * positions) towards the end point.
	 * The move command takes the pen everytime to the newX and the constant Y. 
	 */
	public void drawHorizontalLine() {
		double newX = this.x1; // initially we are x1 
		if (this.x2 > this.x1) { //to identify whether to increment or decrement
			while (newX < this.x2) {
				newX += this.lineCoarse;
				roboControl.MoveToXY(newX, this.y1);
			}
		} else {
			while (newX > this.x2) {
				newX -= this.lineCoarse;
				roboControl.MoveToXY(newX, this.y1);
			}
		}
		roboControl.MoveToXY(x2, y2);
	}

	/**
	 * This method draws an inclined line. The direction is first identified that is towards right or left.
	 * Then incrementally move (because the robot uses swivel arm to reach horizontal
	 * positions) towards the end point.
	 * The move command takes the pen everytime to the newX and the newY. 
	 */
	public void drawInclinedLine() {
		double slope;
		slope = (this.y2 - this.y1) / (this.x2 - this.x1);
		double newX1 = this.x1; // initially we are at the start point
		double newY1 = this.y1; // initially we are at the start point
		if (this.y1 < this.y2) {
			while (newY1 < this.y2) {
				newY1 += this.lineCoarse;
				newX1 = 1 / slope * newY1 - 1 / slope * this.y1 + this.x1;
				roboControl.MoveToXY(newX1, newY1);
			}
		} else {
			while (newY1 > this.y2) {
				newY1 -= this.lineCoarse;
				newX1 = 1 / slope * newY1 - 1 / slope * this.y1 + this.x1;
				roboControl.MoveToXY(newX1, newY1);
			}

		}
		roboControl.MoveToXY(this.x2, this.y2);
	}
	
	/**
	 * This method sets the status whether to draw a line or not
	 */
	public void isSelectedToDraw(){
		this.status="yesDrawLine";
	}
	
	/**
	 * This method return the status whether to draw a line or not
	 */
	public String getStatus(){
		return status;
	}
}
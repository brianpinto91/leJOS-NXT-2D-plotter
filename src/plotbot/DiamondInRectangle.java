package plotbot;

/**
 * Class representing a plottable diamond in a rectangle.
 * @author bp
 *
 */
public class DiamondInRectangle implements Plottable {
	public static final int yStart = 230;
	private double width;
	private double height;
	private PlotbotControl roboControl;
	private String status;
	
	/**
	 * This method plots a diamond in a rectangle by using the PlotbotControl object.
	 * @param PlotbotControl A object of the type PlotbotControl
	 */
	public void plot(PlotbotControl pc){
		this.roboControl = pc;
		drawDiaInRect();
	}
	
	/**
	 * This method draws a diamond in a rectangle by using
	 * the diamond and rectangle objects.
	 */ 
	public void drawDiaInRect(){
		Rectangle rect = new Rectangle();
		rect.setCoord(-width/2, yStart-height, width/2, yStart);
		rect.plot(roboControl);
		Diamond dia = new Diamond();
		dia.setCoord(0, yStart, width/2, yStart-(height/2));
		dia.plot(roboControl);
	}
	 
	/**
	 * This method is used to set the width of the diamond in a rectangle shape
	 * @param width required object width (in mm)
	 */
	public void setWidth(double width){
		this.width = width;
	}
	
	/**
	 * This method is used to set the height of the diamond in a rectangle shape
	 * @param width required object height (in mm)
	 */
	public void setHeight(double height){
		this.height = height;
	}
	
	/**
	 * This method sets the status whether to draw a diamond in a rectangle or not
	 */
	public void isSelectedToDraw(){
		this.status="yesDrawDiamondInRectangle";
	}
	
	/**
	 * This method return the status whether to draw a diamond in a rectangle or not
	 */
	public String getStatus(){
		return this.status;
	}	
}
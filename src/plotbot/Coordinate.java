package plotbot;

/**
 * This class represents the coordinate system on the plot chart and the robot pen's
 * position on it.
 * @author bp
 *
 */
public class Coordinate {
	
	public static double currentX;
	public static double currentY;
	public static double robotCentreY;
	public static double currentAbsoluteAngle;
	
	RoboParameters para = new RoboParameters();
	
	/**
	 * This method sets the initial position for the robot, once the calibration is completed.
	 * The pen will be at the top of the black bar.
	 * At this position the robots coordinates are set as defined in this method.
	 */
	public void setInitialPosition(){
		currentX=0;
		currentY=0;
		robotCentreY=-80;
		currentAbsoluteAngle=90;
	}
	
	/**
	 * This method updates the vertical position of the robot's pen, by the
	 * specified value.
	 * @param y the displacement along y-axis
	 */
	public void updateCurrentPosition(double y){
		currentY+=y;
		robotCentreY+=y;
	}

	/**
	 * This method updates the horizontal position of the robot's pen, by the
	 * specified value.
	 * @param x the change in swivel arm's angle
	 */
	public void updateCurrentAbsoluteAngle(double teta){
		currentAbsoluteAngle+=teta;
		currentX=para.getSwivellArmRadius()*Math.cos((para.getPI()/(180.00))*currentAbsoluteAngle);
		currentY=robotCentreY+para.getSwivellArmRadius()*Math.sin((para.getPI()/(180.00))*currentAbsoluteAngle);
		
	}
	
	/**
	 * This method returns the current x position of the robot's pen
	 * @return the absolute x coordinate position (in mm)
	 */
	public double getCurrentPositionX(){
		return currentX;
	}
	
	/**
	 * This method returns the current y position of the robot's pen.
	 * @return the absolute y coordinate position (in mm)
	 */
	public double getCurrentPositionY(){
		return currentY;
	}
	
	/**
	 * Returns the swivel arm rotation angle required given any X and Y position
	 * @param x the required x coordinate position for the robot's pen
	 * @return the required rotation angle of the swivel arm (in degrees)
	 */
	public double xtoAngle(double x) {
		double rotAngle=(Math.acos(x/para.getSwivellArmRadius()))*(180/3.147)-currentAbsoluteAngle;
		return rotAngle; 
	}
}
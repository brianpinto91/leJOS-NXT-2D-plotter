package plotbot;

import lejos.nxt.*;

/**
 * This class implements the movement controls of the LEJOS robot
 * @author Brian
 *
 */
public class PlotbotControl {
	public Coordinate co;
	RoboParameters para=new RoboParameters();
	
	/**
	 * The constructor creates an instance of coordinate class
	 * and sets the start position of the robot.
	 */
	public PlotbotControl() {
		co = new Coordinate();
		co.setInitialPosition();
	}
	
	/**
	 * This method drives the robot by a specified distance.
	 * @param distance the distance in mm to be moved
	 */
	public void Move(double distance) {
		Motor.C.setSpeed((int) para.getSpeedWheelMot());
		double rotAngle = (distance * para.getOneRotInDeg() * para.getGearRatioWheel()) / (para.getPI() * para.getWheelDia());
		Motor.C.rotate((int) rotAngle);
		co.updateCurrentPosition(distance);
	}

	/**
	 * This method rotates the swivel arm by a specified angle.
	 * @param teta angle in degrees
	 */
	public void rotateSwivelAngle(double teta) {
		Motor.A.setSpeed((int) para.getSpeedSwivMot());
		Motor.A.rotate((int) (teta * para.getGearRatioArm()));
		co.updateCurrentAbsoluteAngle(teta);
	}

	/**
	 * This method moves the robot to a specified xy position
	 * with respect to the calibrated start position.
	 * @param x the absolute horizontal position (in mm)
	 * @param y the absolute vertical position (in mm)
	 */
	public void MoveToXY(double x, double y) {
		double teta = co.xytoANgle(x, y);
		rotateSwivelAngle(teta);
		double motionDist = y - co.getCurrentPositionY();
		Move(motionDist);
	}
	
	/**
	 * This method moves the pen down to touch the plotting surface.
	 */
	public void penDown() {
		Motor.B.rotate((int) -para.getPenTouchAngle());
	}
	
	/**
	 * This method moves the pen up.
	 */
	public void penUp() {
		TouchSensor sensorPen = new TouchSensor(SensorPort.S2);
		if (!sensorPen.isPressed()) {
			Motor.B.rotate((int) para.getPenTouchAngle());
		}
	}
	
	/**
	 * This method can be used to correct for extra drive
	 * of the motor when changing the direction from counter clockwise
	 * to clockwise. 
	 */
	public void prepDirecChangeCW() {
		Motor.A.rotate((int) (-para.getErrAng() * para.getGearRatioArm()));
	}

	/**
	 * This method can be used to correct for extra drive
	 * of the motor when changing the direction from clockwise
	 * to counter clockwise. 
	 */
	public void prepDirecChangeCCW() {
		Motor.A.rotate((int) (para.getErrAng() * para.getGearRatioArm()));
	}
}
package plotbot;

/**
 * Class defining all the mechanical parameters of the LeJOS Robot
 * @author Brian
 */
public class RoboParameters {
	private static final double wheelDia = 56; // in millimeter
	private static final double gearRatioWheel = 5;
	private static final double gearRatioArm = 84;
	private static final double PI = 3.147;
	private static final double oneRotInDeg = 360; 
	private static final double speedWheelMot = 150; // in degrees per second
	private static final double speedSwivMot = 5000; // in degrees per second
	private static final double errAng = 6; // swivel arm backlash compensation in degrees.(Depends on robot)
	private static double maxSwivelAngle;	// in degrees 
	private static double penTouchAngle = 400;  // to be checked by user if its sufficient to make the pen touch
	private static final double swivellArmRadius = 80; // in millimeter
	private static final int lightThreshold = 535;
	private static double maxWidth;	//derived from max swivel angle
	private static double maxHeight = 230; // in millimeter
	private static final double calSpeedWheelMot = 100; //speed during the calibration (deg/sec)
	private static final double calSpeedSwivMot = 300; //speed during the calibration  (deg/sec)
	private static final double speedPenMot = 150; // in degrees per second

	/**
	 * This method returns the speed of the motor that moves the
	 * plotting pen up and down.
	 * @return speed of the motor (in degrees per second)
	 */
	public double getSpeedPenMot() {
		return speedPenMot;
	}
	
	/**
	 * This method returns the speed of the motor that moves the
	 * swivel arm during the calibration.
	 * @return speed of the motor (in degrees per second)
	 */
	public double getCalSpeedSwivMot() {
		return calSpeedSwivMot;
	}
	
	/**
	 * This method returns the speed of the motor that drives the
	 * wheels of the robot during the calibration.
	 * @return speed of the motor (in degrees per second)
	 */
	public double getCalSpeedWheelMot() {
		return calSpeedWheelMot;
	}
	
	/**
	 * This method returns the maximum height of any shape
	 * that can be drawn using the robot.
	 * @return maximum plottable height (in mm)
	 */
	public double getMaxHeight() {
		return maxHeight;
	}
	
	/**
	 * This method returns the maximum width of any shape
	 * that can be drawn using the robot. This is limited by the
	 * length of the swivel arm of the robot and is determined during
	 * the calibration.
	 * @return maximum plottable width (in mm)
	 */
	public double getMaxWidth() {
		return maxWidth;
	}
	
	/**
	 * This method is used to set the maximum width of any shape
	 * that can be drawn using the robot. This is limited by the
	 * length of the swivel arm of the robot and is determined during
	 * the calibration and set accordingly.
	 * @param maximum plottable width (in mm)
	 */
	public void setMaxWidth(double width) {
		maxWidth = width;
	}
	
	/**
	 * This method returns the threshold (normalized value) of the light
	 * sensor that is set to detect the black bar which
	 * acts as a starting reference for plotting.
	 * For setting threshold values see this <a ref="http://www.lejos.org/p_technologies/nxt/nxj/api/lejos/nxt/LightSensor.html">
	 * LEJOS documentation</a>.
	 * @return light sensor threshold
	 */
	public double getLightThreshold() {
		return lightThreshold;
	}
	
	/**
	 * This method returns the swivel arm radius of the robot.
	 * @return swivel arm radius (in mm)
	 */
	public double getSwivellArmRadius() {
		return swivellArmRadius;
	}
	
	/**
	 * This method returns the motor angle required for
	 * the plotting pen to touch the surface. Its value is set
	 * by manually checking whether or not the pen touches the surface.
	 * @return required motor-drive-to angle for pen (in degrees) 
	 */
	public double getPenTouchAngle() {
		return penTouchAngle;
	}
	
	/**
	 * This methods returns the physical limits of the
	 * swivel arm rotation.
	 * @return max swivel arm rotation (in degrees)
	 */
	public double getMaxSwivelAngle() {
		return maxSwivelAngle;
	}
	
	/**
	 * This methods sets the physical limits of the
	 * swivel arm rotation determined during calibration.
	 * @param max swivel arm rotation (in degrees)
	 */
	public void setMaxSwivelAngle(double angle) {
		maxSwivelAngle = angle;
	}
	
	/**
	 * This methods returns the extra drive angle when the motor
	 * changes the direction which needs to be compensated.
	 * @return max swivel arm rotation (in degrees)
	 */
	public double getErrAng() {
		return errAng;
	}
	
	/**
	 * This methods returns the speed of the motor
	 * driving the swivel arm during the plotting.
	 * @return swivel arm motor speed (in degrees per second)
	 */
	public double getSpeedSwivMot() {
		return speedSwivMot;
	}
	
	/**
	 * This methods returns the speed of the motor
	 * driving the wheels of the robot during the plotting.
	 * @return wheels motor speed (in degrees per second)
	 */
	public double getSpeedWheelMot() {
		return speedWheelMot;
	}
	
	/**
	 * This methods returns the degrees corresponding to one
	 * rotation of the motor.
	 * @return degrees in one rotation
	 */
	public double getOneRotInDeg() {
		return oneRotInDeg;
	}
	
	/**
	 * This method returns the value of the constant pi.
	 * @return value of pi
	 */
	public double getPI() {
		return PI;
	}
	
	/**
	 * This method returns the gear ratio of the motor
	 * and the gear driving the swivel arm.
	 * @return swivel arm gear ratio
	 */
	public double getGearRatioArm() {
		return gearRatioArm;
	}
	
	/**
	 * This method returns the gear ratio of the motor
	 * and the gear driving the wheels of the robot.
	 * @return wheels gear ratio
	 */
	public double getGearRatioWheel() {
		return gearRatioWheel;
	}
	
	/**
	 * This method returns the diameter of the robot's wheel.
	 * @return wheel diameter (in mm)
	 */
	public double getWheelDia() {
		return wheelDia;
	}
}
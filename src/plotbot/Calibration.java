package plotbot;

import lejos.nxt.*;

/**
 * This class is used to calibrate the robot by determining the start position
 * on the plotting board and finding the limits of the swivel arm
 * @author bp
 *
 */
public class Calibration {
	private static double exitAngle = 15;
	private static double wheelBacklash = 25;  
	RoboParameters para = new RoboParameters();
	PlotbotControl pc = new PlotbotControl();
	TouchSensor sensorArm;
	TouchSensor sensorPen;
	LightSensor light;

	/**
	 * The constructor calls sequentially the three methods for
	 * calibrating the pen, the swivel arm, and the light sensor. 
	 */
	public Calibration() {
		
		sensorArm = new TouchSensor(SensorPort.S1);
		sensorPen = new TouchSensor(SensorPort.S2);
		light = new LightSensor(SensorPort.S3);
		calibratePen();
		calibrateSwivelArm();
		calibrateLightSensor();
		LCD.drawString("Calibration Completed", 0, 0);
	}

	/**
	 * This method drives the robot to the initial position.
	 * The robot is required to be placed ahead of the black bar on the
	 * plotting board. Then this method drives the robot backward until the
	 * light sensor detects the black bar. The robot is then driven a little forward
	 * so that the pen is at the start of the black bar. This position is 
	 * considered x=0.
	 */
	private void calibrateLightSensor() {
		Motor.C.setSpeed((int) (para.getCalSpeedWheelMot()));
		Motor.C.backward();
		while (light.readNormalizedValue() != para.getLightThreshold()) {
			
		}
		Motor.C.stop();
		Motor.C.rotate((int) wheelBacklash);
		double rotAngle = (25 * para.getOneRotInDeg() * para.getGearRatioWheel()) / (para.getPI() * para.getWheelDia());
		Motor.C.rotate((int) rotAngle);
	}
	
	/**
	 * This method calculates the maximum angle by which the swivel arm can
	 * be rotated before pressing against the limit push sensor is pressed.
	 * The swivel arm is rotated to the right extreme end and then it is rotated towards
	 * the other extreme end and the time is calculated. with the known parameters of motor
	 * speed and the time, the end to end max swivel angle is computed.
	 */
	private void calibrateSwivelArm() {
		Motor.A.setSpeed((int) para.getCalSpeedSwivMot());
		Motor.A.backward();
		while (!sensorArm.isPressed()) {

		}
		Motor.A.stop();
		detectBlackBar();
		Motor.A.rotate((int) (para.getErrAng() * para.getGearRatioArm()));// to compensate backlash error								 
		RoboTimer t = new RoboTimer();
		Motor.A.rotate((int) (exitAngle * para.getGearRatioArm()));/// to make sure the sensor moves out of the black bar
		Motor.A.forward();
		while (light.readNormalizedValue() != para.getLightThreshold()) {

		}
		Motor.A.stop();
		t.stopTimer();
		double timeTaken = t.elapsedSeconds / 1000; // conversion from milliseconds to seconds
		double endToEndAngle = (para.getCalSpeedSwivMot() * timeTaken) / (para.getGearRatioArm());
		double maxSwivelAngle = endToEndAngle / 2;
		para.setMaxSwivelAngle(maxSwivelAngle);
		double maxWidth = 2 * para.getSwivellArmRadius() * Math.cos(maxSwivelAngle * para.getPI() / 180);
		para.setMaxWidth(maxWidth);
		Motor.A.rotate((int) (-para.getMaxSwivelAngle() * para.getGearRatioArm()));
	}

	/**
	 * This method detects the black bar using the light sensor and then
	 * stops the robot.
	 */
	private void detectBlackBar() {
		Motor.C.setSpeed((int) (para.getCalSpeedWheelMot()));
		while (light.readNormalizedValue() != para.getLightThreshold()) {
			Motor.C.backward();
		}
		Motor.C.stop();
	}

	/**
	 * This method calibrates the pen to touch the plotting surface.
	 */
	private void calibratePen() {
		Motor.B.setSpeed((int) para.getSpeedPenMot());
		Motor.B.forward();
		while (!sensorPen.isPressed()){
			
		}
		Motor.B.stop();
		Motor.B.rotate((int) -para.getPenTouchAngle());
		Motor.B.rotate((int) para.getPenTouchAngle());
	}
}
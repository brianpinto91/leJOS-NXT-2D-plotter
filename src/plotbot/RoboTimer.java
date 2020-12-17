package plotbot;
import lejos.util.TimerListener;
import lejos.util.Timer;

/**
 * This class is represents a timer that can keep track of time elapsed
 * with a precision of 100ms. The class is used to determine the angle traversed
 * when a motor is run with a defined speed for time tracked by this timer.
 * @author bp
 *
 */
public class RoboTimer implements TimerListener {
	Timer timer;
	int elapsedSeconds;
	
	/**
	 * The constructor creates a timer object with a trigger for every 100ms 
	 * and starts the timer.
	 */
	public RoboTimer() {
		this.timer = new Timer(100, this);
		this.timer.start();
		this.elapsedSeconds = 0;
	}
	
	/**
	 * This method updates the elapsed time using the inherited method of TimerListner
	 * which is called every 100ms as defined in the timer object.
	 */
	public void timedOut() {
		this.elapsedSeconds+=100; //increment of 100ms
	}
	
	/**
	 * This method is used to stop the timer
	 */
	public void stopTimer(){
		this.timer.stop();
	}
}
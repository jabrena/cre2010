//import lejos.robotics.*;
//import lejos.subsumption.*;
//import lejos.navigation.*;
import lejos.robotics.navigation.TachoPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.subsumption.Arbitrator;
import lejos.nxt.*;
import java.util.ArrayList;
import java.lang.Math;

/**
 * This code completes challenges 1 to 3 of the robot line follower challenge.
 * All code specifics are detailed below, line by line.
 * 
 * This code draws from the sample code provided by Lawrie Griffiths for a crude
 * line follower. All modifications made by Kyle McCarty and Thomas Trebat.
 */
public class Line {
	// Sets the threshold at which the robot considers something under its
	// sensor to be a line.
	static int THRESHOLD = 36;
	// Sets the speed at which the robot moves for default actions. Note that
	// a few actions called for a higher speed and were independently declared.
	final static int SPEED = 120;
	// Indicates the amount of tolerance modification that the left and right
	// sensors should undergo if necessary.
	final static int INC = 0;
	static ArrayList<Integer> nArray; //= new ArrayList<Integer>();
	static int index = 0;

	int orientation = 0;

	public static void main (String[] aArg) throws Exception {
		//RobotGraph rg = new RobotGraph(0, 2, 8);
		//nArray = rg.getPath();
		
		// Variable for the motors controlling the robots wheels. Used for
		// controlling robot motion.
		final TachoPilot pilot = new TachoPilot(2.0f,.875f,Motor.A, Motor.C, false);
		// Variable for the left light sensor.
		final LightSensor sensor1 = new LightSensor(SensorPort.S1);
		// Variable for the center light sensor.
		final LightSensor sensor2 = new LightSensor(SensorPort.S2);
		// Variable for the right light sensor.
		final LightSensor sensor3 = new LightSensor(SensorPort.S3);
		// Variable for brick screen. Used for displaying robot status.
		final LCD screen = new LCD();

		// This behavior controls the robot's actions upon discovering an
		// intersection. SInce intersection detection and action is a major
		// portion of the challenges we completed, this took top priority
		// for execution in the event of a conflict.
		Behavior Intersection = new Behavior() {
			// Variable used for controlling when to stop the method
			boolean suppressor = false;
			// Variable controls the number of spokes the robot should
			// consider before taking one as per challenge 3. 
			int initN = 1;
			// Holds the current number of spokes the robot has detected.
			int n = 0;	
			// This method controls when the Intersection behavior should
			// control the robot. We defined an intersection as anything 
			// that registers as a line to all three sensors simultaneously.
			public boolean takeControl() {
				// Checks to see if the sensor readings are within the range
				// for which they classify as a line.
				//	boolean center = sensor2.readValue() <= THRESHOLD;
				boolean left = sensor1.readValue() <= THRESHOLD + INC;
				boolean right = sensor3.readValue() <= THRESHOLD + INC;

				// Returns true if left and right sensors sense a line
				return left && right /* && center */;
			}

			// This method controls what to do when the method stops. Since
			// we want it to run infinitely until the robot completes its spoke
			// search, we have the thread yield (not return) until it has found
			// the desired number of spokes and has been cleared for closing.
			public void suppress() {suppressor = true; while(suppressor && n > -1) {Thread.yield();}}

			// This method controls what the robot actually does at an intersection
			// as per the specifications of challenge 3.
			public void action() {
				// If the suppressor variable is not properly initialized, such
				// as from a previous run or error, initialize it.
				if(suppressor) {suppressor = !suppressor;}
				// Reset the number of spokes, similar to the above suppressor.
				if(n < 0) {n = initN;}
				if(index < nArray.size())
				{
					n = nArray.get(index);
				}
				else
				{
					n = 0;
				}
				index++;
				// A variable to indicate the previous value read by the light sensor.
				// Used for determining whether a spoke has already been scanned or not.
				int lastVal = sensor2.readValue();

				// Update the robot's display with the relevant information
				// concerning light readings and behavior status.
				screen.clearDisplay();
				screen.drawString("1: " + sensor1.readValue(), 0, 0);
				screen.drawString("2: " + sensor2.readValue(), 0, 1);
				screen.drawString("3: " + sensor3.readValue(), 0, 2);
				screen.drawString("Running Behavior: Intersection", 0, 3);
				screen.drawString("Intersection", 0, 4);

				// Make a quick double beep to indicate that the intersection
				// has been found and is being handled.
				Sound.twoBeeps();

				// Set the pilot's speed to a higher value and order the robot
				// forward briefly. This brings the robot to a position where its
				// central axle is approximately at the location of where its
				// sensors were previously. This allows it to rotate and properly
				// detect the spokes. Stop after the brief pause.
				pilot.setSpeed(250);
				pilot.forward();
				try {Thread.sleep(450);} catch(Exception e) {}
				pilot.stop();

				// Reset the robot's speed to the default and rotate twice. The
				// actual amount rotated seems to be based on speed slightly, but
				// we found the best way to turn the robot around about 160 degrees
				// was to simply run the rotate command twice. The robot is turned
				// for orientation purposes so that can start detecting spokes clockwise
				// from the one it started at. Pause to give the robot time to finish
				// before moving on.
				pilot.setSpeed(SPEED);
				pilot.rotate(360);
				pilot.rotate(360);
				try {Thread.sleep(675);} catch(Exception e) {}

				// While the robot has not completed its goal, keep looking for spokes!
				while (!suppressor) {
					// Rotate a small bit
					pilot.rotate(30, true);
					// Don't continue while the robot is moving
					while (!suppressor && pilot.isMoving()) Thread.yield();

					// Update the display data for the robot's new position
					// and also note the number of spokes left to find
					screen.clearDisplay();
					screen.drawString("1: " + sensor1.readValue(), 0, 0);
					screen.drawString("2: " + sensor2.readValue(), 0, 1);
					screen.drawString("3: " + sensor3.readValue(), 0, 2);

					screen.drawString("Running Behavior", 0, 3);
					screen.drawString("HunterSeeker", 0, 4);
					screen.drawString("N: " + n, 0, 5);

					// If the center light reads a line, we have found a spoke
					if(sensor2.readValue() <= THRESHOLD) {
						// If the previous value was not a line, then we know
						// we have now moved over a line and can mark off a spoke.
						if(lastVal > THRESHOLD) {
							// Mark off one spoke.
							n--;
							// Tell us that the spoke was found.
							Sound.beep();
						}
					}
					// Set the last value to the current value.
					lastVal = sensor2.readValue();
					// If we have no spokes left to find, we're done.
					if(n < 0) {suppressor = true;}
				}
				suppressor = false;
				// Stop moving.
				pilot.stop();
			}
		};

		// This behavior handles the robots most frequently used and simplist action --
		// driving forward. This method was largely derived from the lejos sample code
		// but modified to fit the more sensor rich robot we used, as well as to support
		// our specified default speeds and sensor update information.
		Behavior Forward = new Behavior() {
			// The robot should only drive forward if just its center sensor detects a
			// line. Otherwise, a different behavior should handle the robot's actions.
			public boolean takeControl() {
				// Determine whether each sensor picks up a line.
				boolean center = sensor2.readValue() <= THRESHOLD;
				boolean left = sensor1.readValue() <= THRESHOLD + INC;
				boolean right = sensor3.readValue() <= THRESHOLD + INC;

				// Only return true if only the center sensor detects a line.
				return center && !left && !right;
			}

			// When this method ends, the robot merely needs to stop. So long as only the
			// center sensor picks up a line, the robot will keep going, so there is no
			// need to suppress the thread.
			public void suppress() {pilot.stop();}

			// Simple set of actions for the robot to take. Mainly, just update the data
			// screen for out viewing pleasure and keep chugging along at the default speed.
			public void action() {
				// Update the sensor screen.
				screen.clearDisplay();
				
				screen.drawString("1: " + sensor1.readValue(), 0, 0);
				screen.drawString("2: " + sensor2.readValue(), 0, 1);
				screen.drawString("3: " + sensor3.readValue(), 0, 2);
				screen.drawString("Running Behavior: Drive", 0, 3);
				screen.drawString("Drive", 0, 4);

				// Set the speed to the default value and move forward.
				pilot.setSpeed(SPEED);
				pilot.forward();
			}					
		};

		// This behavior handles the robot's actions when it can no longer read a line with its
		// primary sensor (the central one). The robot will rotate about in an attempt to locate
		// a line to continue following. The basis of this code was derived from the Lejos sample
		// code, but extensively modified to support the extra sensors, which cause the robot to
		// turn differently if a line is detected under one during its search, and to account for
		// data screen updates.
		Behavior FindLine = new Behavior() {
			// Set the variable pertaining to the whether the behavior should hold or not.
			private boolean suppress = false;

			// The behavior should take control if the central sensor no longer reads a line. This
			// means that the robot is no longer following the line and must reorientate itself.
			public boolean takeControl() {
				// Determine if the center sensor is reading a line.
				boolean center = sensor2.readValue() <= THRESHOLD;

				// Return true if it is not.
				return !center;
			}

			// This behavior should only relinquish control when it finds a new line.
			public void suppress() {
				suppress = true;
				while (suppress) Thread.yield();
			}

			// This method contains the actions the robot should take to locate a new lone to follow.
			public void action() {
				// Updates the display screen.
				screen.clearDisplay();
				screen.drawString("1: " + sensor1.readValue(), 0, 0);
				screen.drawString("2: " + sensor2.readValue(), 0, 1);
				screen.drawString("3: " + sensor3.readValue(), 0, 2);
				screen.drawString("Running Behavior", 0, 3);
				screen.drawString("Search", 0, 4);

				// This variable indicates whether the robot should continue in only one direction
				// while turning. This should be false if the robot has no idea where a line is, as
				// this will give it a very high probability of finding a line other than on directly
				// behind it (ostensibly the one it arrived on) first if it exists. However, if the
				// robot detects a line with on of its side sensors, it would be foolish to look the
				// other way rather than continuing.
				boolean initTrue = false;
				// Indicates how far the robot should look, initially.
				int sweep = 20;

				// If the robot can already detect a line with its side sensor, go that way.
				if(sensor3.readValue() <= THRESHOLD) {sweep = -Math.abs(sweep); initTrue = true;}
				if(sensor1.readValue() <= THRESHOLD) {initTrue = true;}

				// Set the robot's speed to default.
				pilot.setSpeed(SPEED);

				// While the robot has no accomplished its goals...
				while (!suppress) {
					// Rotate through the current search angle
					pilot.rotate(sweep,true);
					// Allow the robot to move without interruption.
					while (!suppress && pilot.isMoving()) Thread.yield();

					// If a side sensor detects a line, continue in that direction rather than
					// checking the other way.
					if(sensor3.readValue() <= THRESHOLD) {sweep = -Math.abs(sweep); initTrue = true;}
					if(sensor1.readValue() <= THRESHOLD) {sweep = Math.abs(sweep); initTrue = true;}

					// If the robot has detected a line with a side sensor, but not yet found it,
					// increase the search angle and keep going in the same direction. Otherwise,
					// search a wider angle but in the opposite direction in case something is there.
					if(initTrue) {sweep *= 2;}
					else {sweep *= -2;}

					// Update the display with the current information.
					screen.clearDisplay();
					screen.drawString("1: " + sensor1.readValue(), 0, 0);
					screen.drawString("2: " + sensor2.readValue(), 0, 1);
					screen.drawString("3: " + sensor3.readValue(), 0, 2);
					screen.drawString("Running Behavior", 0, 3);
					screen.drawString("Search", 0, 4);
				}
				// Stop the robot once it has found its goal
				pilot.stop();
				// Reset suppress
				suppress = false;
			}
		};

		// Wait for enter button to be pressed to activate
		Button.ENTER.waitForPressAndRelease();

		// Set all of the desired behaviors that are to be used to activate.
		Behavior[] bArray = {FindLine, Forward, Intersection};
		// Activate all of the desired behaviors.
		(new Arbitrator(bArray)).start();
	}
}


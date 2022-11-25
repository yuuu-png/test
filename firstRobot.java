package team07;

import robocode.HitRobotEvent;
import robocode.TeamRobot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;


/**
 * Walls - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * <p>
 * Moves around the outer edge with the gun facing in.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class firstRobot extends TeamRobot {

	/* phase 0 := go to the near wall
	 * 		 1 := walk by the wall
	 */
	int phase = 0;
	boolean found = false;
	double moveAmount; // How much to move

	/**
	 * run: Move around the walls
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.orange);
		setBulletColor(Color.cyan);
		setScanColor(Color.cyan);

		// Initialize moveAmount to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		turnLeft(getHeading() % 90);
		turnGunLeft(180);
		ahead(moveAmount);
		
		setAdjustGunForRobotTurn(true);


		phase = 1;
		
		while (true) {
			ahead(moveAmount);
			turnRight(90);
		}
	}

	/**
	 * onHitRobot:  Move away a bit.
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			setBack(100);
		} // else he's in back of us, so set ahead a bit.
		else {
			setAhead(100);
		}
		
		
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		setFire(2);
		if(phase == 0){
			found = true;
		}
		if(phase == 1){
		    // Calculate exact location of the robot
			double absoluteBearing = getHeading() + e.getBearing();
			double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
	
			// If it's close enough, fire!
			if (Math.abs(bearingFromGun) <= 3) {
				setTurnGunRight(bearingFromGun);
				if (getGunHeat() == 0) {
					//fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
				}
			} else {
				setTurnGunRight(bearingFromGun);
			}
		}
	}
	
}
package team07;

import robocode.HitRobotEvent;
import robocode.TeamRobot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
import java.util.ArrayList;

public class BaseRobot extends TeamRobot {

	/* phase 0 := go to the near wall
	 * 		 1 := walk by the wall
	 *       2 := battle in the center of field
	 */
	int phase = 0;
	// target's name that we are now attacking
	String target;
	/* record target's coord data. 
	 * FIFO 
	 */
	ArrayList<double[]> targetX, targetY;
	
	public void run(){	
		while(true){
			changePhase();
			move();		
			radar();
			shoot();
		}
		
	}
	
	public void changePhase(){
	}
	public void move(){
	}
	public void radar(){
	}
	public void shoot(){
	}
	
}
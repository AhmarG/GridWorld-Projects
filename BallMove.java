/**
 * BallMove.java
 * 
 * Class to extend special actor movement in
 * terms to demonstrate vector calculation
 * 
 * @author Ahmar Gordon
 * @version 1.3
 */
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * Specific implementation of Bug to demo
 * 2D vector calculation
 */
public class BallMove extends Bug
{
	private int steps;
	private int sideLength;
	private String color;
	private int xsteps;
	private int ysteps;
	private Location startLoc;
	private Location goal;
	private VectorWorld w;
	private LinkedList<Location> path = new LinkedList<Location>(); //keeps track of where you move


	public BallMove(int length, String color)
	{
		steps = 0;
		sideLength = length;
		this.color = color;
	}
	
	/**
	 * Sets goal location for this Ball
	 * @param goal
	 */
	public void setGoalLocation(Location goal)
	{
		this.goal = goal;
	}
	
	/**
	 * Makes sure the Ball can remember where it started
	 * in the grid
	 * @param str
	 */
	public void setStartLoc(Location str)
	{
		this.startLoc = str;
	}
	/**
	 * Sets world associated with this Ball
	 * @param w VectorWorld
	 */
	public void setWorld(VectorWorld w)
	{
		this.w = w;
	}
	
	/**
	 * Main movement methods. Contains error checking
	 */
	public void act()
	{
		if(steps == 0)
			path.add(getLocation());
		
		if(getDirection() == 0 || getDirection() == 180)
			moverY();
		else if(getDirection() == 90 || getDirection() == 270)
			moverX();
		else
			mover();

		if(atGoal())
		{
			JOptionPane.showMessageDialog(null, calcVector(),
					"Goal location reached!", JOptionPane.INFORMATION_MESSAGE);
			reset();
		}

	}
	
	/**
	 * Helper for act(). Moving left or right
	 */
	private void moverX()
	{
		if (canMove())
		{
			Location temp = getLocation();
			move();
			if(!visited())
			{
				xsteps++;
				steps++;
				path.add(getLocation());
			}
			else
			{
				xsteps--;
				steps--;
				getGrid().remove(temp);
				path.remove(temp);
			}
			
		}
		else
		{
			turn();
			turn();
			//xsteps = 0;
			//steps = 0;
		}
		
		if(steps >= sideLength)
		{
			String x = "You've taken many steps!\nTry a more direct path to the goal";
			JOptionPane.showMessageDialog(null, x,
					"Lets try that again", JOptionPane.WARNING_MESSAGE);
			reset();
		}
	}
	
	/**
	 * Helper for act(). Moving up or down
	 */
	private void moverY()
	{
		if (canMove())
		{
			Location temp = getLocation();
			move();
			if(!visited())
			{
				ysteps++;
				steps++;
				path.add(getLocation());
			}
			else
			{
				ysteps--;
				steps--;
				getGrid().remove(temp);
				path.remove(temp);
			}

		}
		else
		{
			turn();
			turn();
			//ysteps = 0;
			//steps = 0;
		}
		
		if(steps >= sideLength)
		{
			String x = "You've taken many steps!\nTry a more direct path to the goal";
			JOptionPane.showMessageDialog(null, x,
					"Lets try that again", JOptionPane.WARNING_MESSAGE);
			reset();
		}
	}
	
	/**
	 * Helper for act(). Moving diagonally
	 */
	private void mover()
	{
		if (canMove())
		{
			Location temp = getLocation();
			move();
			if(!visited())
			{
				xsteps++;
				ysteps++;
				steps++;
				path.add(getLocation());
			}
			else
			{
				xsteps--;
				ysteps--;
				steps--;
				getGrid().remove(temp);
				path.remove(temp);
			}

		}
		else
		{
			turn();
			turn();
			//steps = 0;
		}
		if(steps >= sideLength)
		{
			String x = "You've taken many steps!\nTry a more direct path to the goal";
			JOptionPane.showMessageDialog(null, x,
					"Lets try that again", JOptionPane.WARNING_MESSAGE);
			reset();
		}
	}
	
	/**
	 * Prints progress made so far by steps
	 */
	public void getSteps()
	{
		System.out.println(color + " ball total steps: " + steps);
		System.out.println(color + "X steps: " + xsteps);
		System.out.println(color + "Y steps: " + ysteps);
	}

	/**
	 * checks to see if you have reached the goal location
	 * @return TRUE if at goal location
	 */
	private boolean atGoal()
	{
		if(getLocation().equals(goal))
		{
			return true;
		}
		return false;
	}

	/**
	 * checks to see if the current location has been occupied before
	 * @return
	 */
	private boolean visited()
	{
		return path.contains(getLocation());
	}

	public void faceEast()
	{
		setDirection(90);
	}
	
	public void faceSouth()
	{
		setDirection(180);
	}
	
	public void faceWest()
	{
		setDirection(270);
	}
	
	public void faceNorth()
	{
		setDirection(0);
	}
	
	/**
	 * final vector calculation for ball
	 * @return vector as a string
	 */
	protected String calcVector()
	{
		double vector = Math.sqrt(Math.pow(xsteps, 2) + Math.pow(ysteps, 2));
		System.out.println("Vector = " + vector);
		String x = "Equation used: √("+xsteps+"^2)+("+ysteps+"^2)\n\n";
		return x+"Vector = " + vector;
	}
	
	/**
	 * Sends the Ball back to where it started upon 
	 * reaching its goal
	 */
	private void reset()
	{
		moveTo(startLoc);
		for(int x = 0; x < path.size(); x++)
		{
			if(getGrid().get(path.get(x)) instanceof Flower)
			{
				getGrid().remove(path.get(x));
			}
			
		}
		path.clear();
		steps=0;
		xsteps=0;
		ysteps=0;
		Goal f = new Goal(Color.GRAY);
		w.add(goal, f);

	}
	
	
}

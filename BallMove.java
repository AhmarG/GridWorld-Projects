import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class BallMove extends Bug
{
	private int steps;
	private int sideLength;
	private String color;
	private int xsteps;
	private int ysteps;
	private Location goal;
	private LinkedList<Location> path = new LinkedList<Location>();


	public BallMove(int length, String color)
	{
		steps = 0;
		sideLength = length;
		this.color = color;
	}

	public void setGoalLocation(Location goal)
	{
		this.goal = goal;
	}
	
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
			JOptionPane.showMessageDialog(null, calcVector(), "Goal location reached!", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println(color + " ball - goal location reached");
			//calcVector();
			//moveTo(new Location(0,0));
		}

	}

	private void moverX()
	{
		if (steps < sideLength && canMove())
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
	}

	private void moverY()
	{
		if (steps < sideLength && canMove())
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
	}

	private void mover()
	{
		if (steps < sideLength && canMove())
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
				path.remove(temp);
			}

		}
		else
		{
			turn();
			turn();
			//steps = 0;
		}
	}

	public void getSteps()
	{
		System.out.println(color + " ball total steps: " + steps);
		System.out.println(color + "X steps: " + xsteps);
		System.out.println(color + "Y steps: " + ysteps);
	}

	private boolean atGoal()
	{
		if(getLocation().equals(goal))
		{
			return true;
		}
		return false;
	}

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
	
	protected String calcVector()
	{
		double vector = Math.sqrt(Math.pow(xsteps, 2) + Math.pow(ysteps, 2));
		System.out.println("Vector = " + vector);
		return "Vector = " + vector;
	}
	
}

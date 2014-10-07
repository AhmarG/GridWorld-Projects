import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;

public class VectorWorld extends ActorWorld
{
	
	BallMove b1;
	BallMove b2;
	public VectorWorld(BallMove b1, BallMove b2)
	{
		this.b1 = b1;
		this.b2 = b2;
		setGrid(new BoundedGrid<Actor>(11,13));
	}
	
	public boolean keyPressed(String description, Location loc)
	{
		if(description.equals("D"))
		{
			b1.faceEast();
			return true;
		}
		
		if(description.equals("A"))
		{
			b1.faceWest();
			return true;
		}
		
		if(description.equals("S"))
		{
			b1.faceSouth();
			return true;
		}
		
		if(description.equals("W"))
		{
			b1.faceNorth();
			return true;
		}
		
		if(description.equals("G"))
		{
			b1.act();
			return true;
		}
		
		if(description.equals("E"))
		{
			b1.turn();
			return true;
		}
		//---------------------------------
		if(description.equals("L"))
		{
			b2.faceEast();
			return true;
		}
		
		if(description.equals("J"))
		{
			b2.faceWest();
			return true;
		}
		
		if(description.equals("K"))
		{
			b2.faceSouth();
			return true;
		}
		
		if(description.equals("I"))
		{
			b2.faceNorth();
			return true;
		}
		
		if(description.equals("H"))
		{
			b2.act();
			return true;
		}
			
		return false;
	}
	

}

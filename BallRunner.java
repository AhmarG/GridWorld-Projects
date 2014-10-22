import info.gridworld.grid.Location;

import java.awt.Color;

public class BallRunner 
{
	public static void main(String[] args)
    {
        //ActorWorld world = new ActorWorld();
        BallMove ball = new BallMove(20, "Red");
        ball.setColor(Color.RED);
        BallMove ball2 = new BallMove(20, "Blue");
        ball2.setColor(Color.BLUE);
        Goal goal = new Goal(Color.GRAY);
        VectorWorld world = new VectorWorld(ball, ball2);
        //BoxBug bob = new BoxBug(3);
        
        world.add(new Location(7, 2), ball);
        world.add(new Location(7, 10), ball2);
        world.add(new Location(3, 6), goal);
        ball.setGoalLocation(goal.getLocation());
        ball.setStartLoc(ball.getLocation());
        ball.setWorld(world);
        ball2.setGoalLocation(goal.getLocation());
        ball2.setStartLoc(ball2.getLocation());
        ball2.setWorld(world);
        world.setMessage("Appalachain State University Computer Science ©\t\t"
        		+ "Author: Ahmar Gordon\n"
        		+ "Physics Vector Demo\t\t"
        		+ "Objective: Move a ball to the goal location");
        world.show();
        
    }
}

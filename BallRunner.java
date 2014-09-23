import info.gridworld.grid.Location;

import java.awt.Color;

public class BallRunner 
{
	public static void main(String[] args)
    {
        //ActorWorld world = new ActorWorld();
        BallMove ball = new BallMove(10, "Red");
        ball.setColor(Color.RED);
        BallMove ball2 = new BallMove(10, "Blue");
        ball2.setColor(Color.BLUE);
        Goal goal = new Goal(Color.GRAY);
        VectorWorld world = new VectorWorld(ball, ball2);
        //BoxBug bob = new BoxBug(3);
        
        world.add(new Location(7, 2), ball);
        world.add(new Location(7, 8), ball2);
        world.add(new Location(4, 5), goal);
        ball.setGoalLocation(goal.getLocation());
        ball2.setGoalLocation(goal.getLocation());
        world.setMessage("Move a ball to the goal location");
        world.show();
        
    }
}

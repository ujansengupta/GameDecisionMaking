package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;
import utility.GameConstants;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class OutOfBoundsAction extends Task
{
    public OutOfBoundsAction(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        Engine.enemy.Align(GameConstants.SCR_CENTER);
        Engine.enemy.Seek(GameConstants.SCR_CENTER);
        return returnType.SUCCEEDED;
    }
}

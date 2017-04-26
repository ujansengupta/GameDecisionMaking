package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class OutOfBoundsDecision extends Task
{
    public OutOfBoundsDecision(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        if (Engine.enemy.outOfBounds())
            return returnType.SUCCEEDED;

        return returnType.FAILED;
    }
}

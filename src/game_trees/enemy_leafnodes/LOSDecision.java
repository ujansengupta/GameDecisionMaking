package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;
import objects.Enemy;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class LOSDecision extends Task
{

    public LOSDecision(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        if (Engine.enemy.hasLOS(Engine.player.getPosition()))
            return returnType.SUCCEEDED;


        return returnType.FAILED;
    }
}

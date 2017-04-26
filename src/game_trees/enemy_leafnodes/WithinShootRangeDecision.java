package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;
import objects.Enemy;
import processing.core.PVector;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class WithinShootRangeDecision extends Task
{
    public WithinShootRangeDecision(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        if (PVector.sub(Engine.player.getPosition(), Engine.enemy.getPosition()).mag() < Enemy.shootRadius)
            return returnType.SUCCEEDED;

        return returnType.FAILED;
    }
}

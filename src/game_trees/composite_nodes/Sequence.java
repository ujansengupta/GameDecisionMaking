package game_trees.composite_nodes;

import engine.Engine;
import game_trees.EnemyBehaviourTree;
import objects.Enemy;

/**
 * Created by ujansengupta on 4/16/17.
 */
public class Sequence extends Task
{
    public int childIndex = 0;

    public Sequence(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        for (int i = childIndex; i < children.size(); i++)
        {
            returnType result = children.get(i).run();

            if (result == returnType.FAILED)
            {
                EnemyBehaviourTree.blackboard.map.put(taskID, returnType.FAILED);
                return returnType.FAILED;
            }

            else if (result == returnType.RUNNING)
            {
                EnemyBehaviourTree.blackboard.map.put(taskID, returnType.RUNNING);
                childIndex = i;
                return returnType.RUNNING;
            }

            if (childIndex == children.size() - 1)
                childIndex = (childIndex + 1) % children.size();
        }

        EnemyBehaviourTree.blackboard.map.put(taskID, returnType.SUCCEEDED);
        return returnType.SUCCEEDED;

    }
}

package game_trees.composite_nodes;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class Decorator_AlwaysTrue extends Task
{
    public Decorator_AlwaysTrue(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        for (Task t : children)
            t.run();

        return returnType.SUCCEEDED;
    }
}

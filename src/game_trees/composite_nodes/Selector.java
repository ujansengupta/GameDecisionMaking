package game_trees.composite_nodes;

/**
 * Created by ujansengupta on 4/16/17.
 */
public class Selector extends Task
{
    public Selector(int taskID)
    {
        super(taskID);
    }

    @Override
    public Task.returnType run()
    {
        for (Task t : children)
        {
            returnType result = t.run();

            if (result == returnType.SUCCEEDED)
            {
                setState(returnType.SUCCEEDED);
                return returnType.SUCCEEDED;
            }

            else if (result == returnType.RUNNING)
            {
                setState(returnType.RUNNING);
                return returnType.RUNNING;
            }
        }

        return returnType.FAILED;
    }
}

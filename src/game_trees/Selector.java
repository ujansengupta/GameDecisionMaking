package game_trees;

/**
 * Created by ujansengupta on 4/16/17.
 */
public class Selector extends Task
{
    @Override
    public Task.returnType run()
    {
        for (Task t : children)
        {
            returnType result = t.run();
            if (result == returnType.SUCCEEDED || result == returnType.RUNNING)
                return returnType.SUCCEEDED;
        }

        return returnType.FAILED;
    }
}

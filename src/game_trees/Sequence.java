package game_trees;

/**
 * Created by ujansengupta on 4/16/17.
 */
public class Sequence extends Task
{
    public int childIndex = 0;

    @Override
    public returnType run()
    {
        if (state == returnType.RUNNING)
        {
            if (whichChildRunning() == -1)
                childIndex = (childIndex++) % children.size();
            else
                return returnType.RUNNING;
        }

        for (int i = childIndex; i < children.size(); i++)
        {
            returnType result = children.get(i).run();

            if (result == returnType.FAILED)
                return returnType.FAILED;

            else if (result == returnType.RUNNING)
            {
                setState(returnType.RUNNING);
                childIndex = i;
                return returnType.RUNNING;
            }
        }

        return returnType.SUCCEEDED;

    }
}

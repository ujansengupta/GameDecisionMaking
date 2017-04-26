package game_trees;

import game_trees.composite_nodes.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class Blackboard
{
    public Map<Integer, Task.returnType> map;

    public Blackboard()
    {
        map  = new HashMap<>();
    }
}

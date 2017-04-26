package engine;

import objects.Enemy;
import objects.Player;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import utility.GameConstants;

import java.util.ArrayList;
import java.util.List;
import environment.*;

public class Engine extends PApplet
{

    public static Player player;        //Changed these 2 to static, since only one instance of each, and to provide ease of access
    public static Environment environment;
    public static Enemy enemy;
    public static EnemyLogger enemyLogger;

    public static List<Obstacle> staticObjects;

    public static void main(String[] args){
        PApplet.main("engine.Engine", args);
    }


    public void settings()
    {
        size(GameConstants.SCR_WIDTH, GameConstants.SCR_HEIGHT);
    }

    public void setup()
    {
        rectMode(PConstants.CENTER);

        player = new Player(this);
        enemy = new Enemy(this);
        environment = new Environment(this);
        //enemyLogger = new EnemyLogger(this, enemy, 50);
        staticObjects = new ArrayList<>();

        for (Obstacle o : environment.getObstacles())
            staticObjects.add(o);

        frameRate(60);

    }


    public void draw()
    {
        background(105, 183, 219);
        environment.update();
        player.update();
        enemy.update();
        //enemyLogger.update();
    }

    /*public void mouseMoved()
    {
        player.updateTarget();
    }
    public void mousePressed()
    {
        player.shoot();
    }*/

    public static void reset()
    {
        //enemyLogger.writeToFile("test1");
        player.reset();
        enemy.reset();
    }

    public void mouseClicked()
    {
        player.updateTarget();
    }
}

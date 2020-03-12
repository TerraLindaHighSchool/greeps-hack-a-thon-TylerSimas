import greenfoot.*;

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author (your name here)
 * @version 0.2
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }
    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }

    /**
     * Do what a greep's gotta do.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
        if (carryingTomato()) {
            if (atShip()) {
                dropTomato();
                turn(180);
            }
            else {
                if(atWater() || atWorldEdge())
                {
                   turnAround();
                   move();
                }
                else
                {
                   turnHome();
                   move();
                   spit("red");
                }
            }
        }
        else {
            checkFood();
            if (atWater() || atWorldEdge() || seePaint("orange")){
                turnAround();
                move();
            }
            else if(seePaint("red"))
            {
                followPaint();
            }   
            else 
            if (seePaint("purple"))
               {
                turn(45);
               }
            move();
        }
    }
    
    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        // check whether there's another creature here
        Creature anotherGreep = (Creature) getOneIntersectingObject(Creature.class);
        if(tomatoes != null) {
            spit("purple");
            if(anotherGreep != null && tomatoes != null){
                loadTomato();
                turnHome();
            }
              
            {
              spit("red");
            }
        }  
    }

    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Anonymous";  // write your name here!
    }

    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if (carryingTomato()) {
            return "greep-with-food.png";
        }
        else {
            return "greep.png";
        }
    }
    
    public void turnAround()
    {
        if(atWater() || atWorldEdge())
        {
            spit("orange");
            turn(Greenfoot.getRandomNumber(60));
        }
    }
   
    public void getAwayFromOrange()
    {
        if(seePaint("orange"))
        {
            turn(Greenfoot.getRandomNumber(120) + 20);
        }
    }
    
    public void followPaint()
    {
        if(seePaint("red"))
        {
            turnHome();
            turn(180);
        }
    }
}
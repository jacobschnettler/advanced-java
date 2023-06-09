import java.awt.*;

public class MovingPic extends Thread
{
    private int delay;
    private int x;
    private int y;
    private int xChange, yChange;
    private Image pic;
    private boolean alive;

    public void setAlive(boolean inAlive)
    {
        alive = inAlive;
    }
   
    public void setX(int inX)
    {
        x = inX;
    }
   
    public void setY(int inY)
    {
        y = inY;
    }
   
    public void setXChange(int inXc)
    {
        xChange = inXc;
    }
   
    public void setYChange(int inYc)
    {
        yChange = inYc;
    }
   
    public Image getImage()
    {
        return pic;
    }
   
    public boolean getAlive()
    {
        return alive;
    }
   
    public int getX()
    {
        return x;
    }
   
    public int getY()
    {
        return y;
    }
   
    public int getXChange()
    {
        return xChange;
    }
   
    public int getYChange()
    {
        return yChange;
    }
   
    public int getDelay()
    {
        return delay;
    }

    public void setDelay(int inDelay)
    {
        if (inDelay > 0)
            delay = inDelay;
        else
            delay = 20;
    }
   
    public MovingPic(Image inPic, int inDelay, int inX, int inY, int inXc, int inYc)
    {
        //setDelay(inDelay);

        delay = 150;
        
        x = inX;
        y = inY;

        //yChange = inYc;
        //xChange = inXc;
        
        xChange = 20;
       
        pic = inPic;

        alive = false;
    }

    public void run()
    {
        while(alive)
        {
            x = x + xChange;
            y = y + yChange;
        
            try {
                sleep(delay);
            } catch(Exception e){}
        } 
    }

}
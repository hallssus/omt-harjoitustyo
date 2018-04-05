
package parts;
import java.util.*;

public class Snake {
    
    private int height;
    private int width;
    private boolean isOn;
    private Worm worm;
    private Apple apple;

    public Snake(int height, int width) {
        
        this.height = height;
        this.width = width;
        boolean applePlaceNotOk = true;
        while(applePlaceNotOk){ //if it hits the worm its not ok
            int x = new Random().nextInt(width);
            int y = new Random().nextInt(height);
            
            apple = new Apple(x, y);
            
            if (!worm.hitsAPiece(apple)){
                System.out.println("moijo");
                applePlaceNotOk = false;
            }
            this.isOn = true;
            
        }  
    }

    public String getAppleCoordinates() {
        return apple.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Worm getWorm() {
        return worm;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    public boolean getIsOn() {
        return isOn;
    }
    
    public void update(){
        
    }

    @Override
    public String toString() {
        String text = "Place of the worm: (" + worm.getHeadX() + "," + worm.getHeadY() + "). Place of the apple: (" + getAppleCoordinates();
        return text;
    }
    
    
    
    
    
    
    
}

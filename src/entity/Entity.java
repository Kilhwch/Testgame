

package entity;

import graphics.Screen;
import java.util.Random;
import level.Level;

public abstract class Entity {
    
    protected double x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    
    public void update() {
        
    }
    
    public void render(Screen screen) {
        
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void remove() {
        // remove from level
        removed = true;
    }
    
    public boolean isRemoved() {
        return removed;
    }
    
    public void init(Level level) {
        this.level = level;
    }
}

package entity.spells;

import entity.Entity;
import graphics.Screen;
import graphics.Sprite;

public abstract class Spell extends Entity {

    protected boolean removed = false;
    public Sprite sprite;
    public double x, y;
    public final int direction;
    public double speed = 4;
    public double range = 60;
    
    
    
    public Spell(double x, double y, int dir) {
        this.x = x;
        this.y = y;
        this.direction = dir;
    }
   
    
    public void move() {
        
    }
}

package entity.mob;

import entity.Entity;
import entity.spells.Fire;
import entity.spells.Spell;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 1;
    protected boolean moving = false;
    
    

    public void move(double xAxis, double yAxis) {
        if (xAxis != 0 && yAxis != 0) {
            move(xAxis, 0);
            move(0, yAxis);
            return;
        }

        if (xAxis > 0) dir = 1;
        if (xAxis < 0) dir = 3;
        if (yAxis > 0) dir = 2;
        if (yAxis < 0) dir = 0;

        if (!collision(xAxis, yAxis)) {
            x += xAxis;
            y += yAxis;
        }
        
    }
    
    
    public void cast(double x, double y, int dir) {
        Spell spell = new Fire(x, y, dir);
        level.add(spell);
    }

    @Override
    public void update() {
    }

    public boolean collision(double xAxis, double yAxis) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            double xt = ((x + xAxis) + c % 2 * 12 - 37) / 16;

            double yt = ((y + yAxis) - 1.5 / 2 * 16.5) / 16.5;

            int ix = (int) Math.ceil(xt); // transform doubles into int for level.getTile()
            int iy = (int) Math.ceil(yt);
            if (level.getTile(ix, iy).solid()) {
                solid = true;
            }
        }
        
        return solid;
    }

    public void render() {

    }
}

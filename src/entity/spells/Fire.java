

package entity.spells;

import graphics.Screen;
import graphics.Sprite;
import level.tile.Tile;

public class Fire extends Spell {

    private int counter = 0;
    public static final int SPELL_RATE = 15;
    
    public Fire(double x, double y, int dir) {
        super(x, y, dir);
        sprite = Sprite.magic_fire;
    }
    
    @Override
    public void update() {
        move();
    }
    
    public void move() {
        if (lengthCheck()) {
            if (direction == 1) x += speed;
            if (direction == 3) x -= speed;
            }
    }
    
    
    public boolean lengthCheck() {
        if (counter+1 < range) {
            counter++;
            return true;
        }
        else removed = true;
        remove();
        return false;
    }


    public void render(Screen screen) {
        if (!removed)
        screen.renderSprite((int)x - 16, (int)y - 16, sprite);
    }


    
    

    
}

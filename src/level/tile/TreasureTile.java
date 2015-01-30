package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class TreasureTile extends Tile {

    public TreasureTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    @Override
    public boolean solid() {
        return true;
    }

    @Override
    public boolean breakable() {
        return true;
    }
    
    
}

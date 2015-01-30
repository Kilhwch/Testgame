package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class GroundTile extends Tile {

    public GroundTile(Sprite sprite) {
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
}

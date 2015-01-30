package level.tile;
import graphics.Screen;
import graphics.Sprite;

public class Tile {
    
    public int x, y;
    public Sprite sprite;
    
    public static Tile ground = new GroundTile(Sprite.ground);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    public static Tile treasure = new TreasureTile(Sprite.treasure);
    public static Tile sky = new SkyTile(Sprite.sky);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public void render(int x, int y, Screen screen) {
        
    }
    
    public boolean solid() {
        return false;
    }
    
    public boolean breakable() {
        return false;
    }
}

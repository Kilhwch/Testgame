package level;
import entity.Entity;
import graphics.Screen;
import java.util.ArrayList;
import java.util.List;
import level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    
    private List<Entity> entities = new ArrayList<Entity>();
    
    public Level(int width, int height) {
        this.width = height;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }
    
    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    public List<Entity> getEntities() {
        return entities;
    }
    
    

    protected void generateLevel() {
        
    }

    protected void loadLevel(String path) {
        
    }
    
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }
    
    private void time() {
        
    }
    
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;
        
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }
    }
    
    public void add(Entity e) {
        e.init(this);
        entities.add(e);
    }
    
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.sky;
        if (tiles[x + y * width] == 0xFF00FF00) {
            return Tile.ground;
        }
        if (tiles[x + y * width] == 0xFF880015) {
            return Tile.treasure;
        }
        if (tiles[x + y * width] == 0xFF38DAFF) {
            return Tile.sky;
        }
        return Tile.sky;
    }
}
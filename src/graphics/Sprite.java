package graphics;

public class Sprite {
    
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite ground = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite sky = new Sprite(16, 1, 1, SpriteSheet.tiles); // 
    public static Sprite voidSprite = new Sprite(16, 0);
    
    
    public static Sprite treasure = new Sprite(16, 0, 1, SpriteSheet.tiles);
    
    public static Sprite player_left = new Sprite(32, 3, 0, SpriteSheet.tiles);
    public static Sprite player_right = new Sprite(32, 6, 0, SpriteSheet.tiles);
    
    public static Sprite player_wleft1 = new Sprite(32, 2, 0, SpriteSheet.tiles);
    public static Sprite player_wleft2 = new Sprite(32, 4, 0, SpriteSheet.tiles);
    public static Sprite player_wright1 = new Sprite(32, 5, 0, SpriteSheet.tiles);
    public static Sprite player_wright2 = new Sprite(32, 7, 0, SpriteSheet.tiles);
    
    public static Sprite magic_fire = new Sprite(16, 2, 0, SpriteSheet.tiles);
    
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    public Sprite(int size, int colour) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }
    
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = colour;
        }
    }
    
}

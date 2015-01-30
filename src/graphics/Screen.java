package graphics;
import entity.mob.Player;
import java.util.Random;
import level.tile.Tile;


public class Screen {
    
    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 32;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset, yOffset;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }
    
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    
    public void renderTile(int xPosition, int yPosition, Tile tile) {
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xPosition;
                if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
    
    public void renderSprite(int xPosition, int yPosition, Sprite sprite) {
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0; y < sprite.SIZE; y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xAbsolute = x + xPosition;
                if (xAbsolute < -sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
                
                int color = sprite.pixels[x + y * sprite.SIZE];
                if (color != 0xffFFFFFF)
                pixels[xAbsolute + yAbsolute * width] = color;
            }
        }
    }
    
    public void renderPlayer(int xPosition, int yPosition, Sprite sprite) {
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0; y < 32; y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < 32; x++) {
                int xAbsolute = x + xPosition;
                if (xAbsolute < -32 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
                int color = sprite.pixels[x + y * 32];
                if (color != 0xffFFFFFF) { // tyhjät voidpixelit hahmon ympärillä, huom 6 vikaa nroa itse väri
                pixels[xAbsolute + yAbsolute * width] = color;
                }
            }
        }
    }
    
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}

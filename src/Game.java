

import entity.mob.Player;
import entity.spells.Spell;
import input.Keyboard;
import graphics.Screen;
import graphics.Sprite;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import level.Level;
import level.SpawnLevel;


public class Game extends Canvas implements Runnable {

    public static int width = 350;
    public static int height = 200;
    public static int scale = 3;
    private Thread thread;
    
    private Screen screen;
    private JFrame frame;
    private Keyboard keyboard;
    private Level level;
    private Player player;
    
    private Boolean running = false;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        screen = new Screen(width, height);
        frame = new JFrame();
        keyboard = new Keyboard();
        level = new SpawnLevel("/SpawnLevel.png");
        player = new Player(150, 150, keyboard); // spawn pos
        player.init(level);
        
        addKeyListener(keyboard);
    }
    
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle("Test " + " | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    

    public void update() {
        keyboard.update();
        player.update();
        level.update();
    }
    
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        double xScroll = player.getX() - screen.width / 2;
        double yScroll = player.getY() - screen.height / 1.5;
        level.render((int)xScroll, (int)yScroll, screen);
        player.render(screen);
        
        
        
        
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
    
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        Game game = new Game();
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        
        game.start();
    }
}

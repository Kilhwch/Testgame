package entity.mob;

import entity.Entity;
import entity.spells.Fire;
import entity.spells.Spell;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private final double movement_speed = 1;

    // movement
    private int animation = 0;
    private boolean walking = false;

    // grav
    private double gravity = 1.5;
    private int gravFrames = 0;
    private boolean falling = true;
    private boolean jumping = false;

    // jump
    private double maxHeight = 0;
    private boolean heightSet = false;
    private double jumpSpeed = 4.5;
    private int jumpFrames = 0;
    
    // actions
    private int fireRate = 0;


    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_right;

    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_right;
        fireRate = Fire.SPELL_RATE;
    }

    
    @Override
    public void update() {
        if (fireRate > 0) fireRate--;
        double xAxis = 0, yAxis = 0;
        if (animation < 500) {
            animation++;
        } else {
            animation = 0;
        }

        if (input.up && !jumping && !falling) {
            jumping = true;
        }

        if (input.left) {
            xAxis -= movement_speed;
        }

        if (input.right) {
            xAxis += movement_speed;
        }

        if (input.cast && fireRate <= 0) {
            cast(x, y, dir);
            fireRate = Fire.SPELL_RATE;
        }

        if (xAxis != 0 || yAxis != 0) {
            move(xAxis, yAxis);
            walking = true;
        } else {
            walking = false;
        }

        // updates
        clear(); // spells from screen
        gravity();
        if (jumping) jump();
        
    }
    
    private void clear() {
        for (int i = 0; i < level.getEntities().size(); i++) {
            Entity spell = level.getEntities().get(i);
            if (spell.isRemoved()) level.getEntities().remove(i);
        }
    }

    public void gravity() {
        if (collision(0, gravity)) { // collision check
            falling = false;
            gravFrames = 0;
            gravity = 1.5;
        } else { // falling
            falling = true;
            gravFrames++;

            if (gravFrames >= 41 && gravFrames <= 50 && gravity <= 2.6) {
                gravity = gravity + 0.2;
            } else if (gravFrames >= 51 && gravity <= 2.6) {
                gravity = gravity + 0.2;
            }
            y = y + gravity;
        }
    }

    public void jump() {

        if (!heightSet) {
            jumpMaxHeight();
        }
        if (y + jumpSpeed > maxHeight) {
            jumpFrames++;
            // jumping acceleration below
            if (jumpFrames <= 0 && jumpFrames <= 10) {
                jumpSpeed = 4.5;
            } else if (jumpFrames >= 11 && jumpFrames <= 20) {
                jumpSpeed = 3.7;
            } else if (jumpFrames >= 21 && jumpFrames <= 30) {
                jumpSpeed = 2.6;
            }
            y = y - jumpSpeed;

            // now falling ->
        } else {
            heightSet = false;
            maxHeight = 0;
            jumping = false;
            falling = true;
            jumpSpeed = 4.5;
            jumpFrames = 0;
        }
    }

    public void jumpMaxHeight() {
        heightSet = true;
        maxHeight = y - 60;
    }

    @Override
    public void render(Screen screen) {
        if (dir == 1) {
            sprite = Sprite.player_right;
            if (walking && !falling && !jumping) {
                if (animation % 20 > 10) {
                    sprite = Sprite.player_wright1;
                } else {
                    sprite = Sprite.player_wright2;
                }
            }
        }
        if (dir == 3) {
            sprite = Sprite.player_left;
            if (walking && !falling && !jumping) {
                if (animation % 20 > 10) {
                    sprite = Sprite.player_wleft1;
                } else {
                    sprite = Sprite.player_wleft2;
                }
            }
        }

        screen.renderPlayer((int) x - 32, (int) y - 32, sprite);
    }

}

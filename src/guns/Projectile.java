package guns;
import utils.ct;

import java.awt.*;
import java.awt.geom.Point2D;

public class Projectile {
    private float x, y;
    private final float speed;
    private final Point2D.Float direction;
    private final Rectangle hitbox;
    private boolean active = true;

    public Projectile(float startX, float startY, float targetX, float targetY, float speed) {
        this.x = startX;
        this.y = startY;
        this.speed = speed;
        this.hitbox = new Rectangle((int)x, (int)y, 10, 10); // Adjust size as needed

        // Calculate direction vector
        float dx = targetX - startX;
        float dy = targetY - startY;
        float length = (float)Math.sqrt(dx*dx + dy*dy);
        this.direction = new Point2D.Float(dx/length, dy/length);
    }

    public void update() {
        if (!active) return;

        // Move projectile
        x += direction.x * speed;
        y += direction.y * speed;
        hitbox.setLocation((int)x, (int)y);

        // Deactivate if out of bounds
        if (x < 0 || x > ct.GameCT.GAME_WIDTH || y < 0 || y > ct.GameCT.GAME_HEIGHT) {
            active = false;
        }
    }

    public void render(Graphics g) {
        if (!active) return;
        g.setColor(Color.RED); // Or use your projectile image
        g.fillRect((int)x, (int)y, 10, 10);
    }

    // Getters
    public boolean isActive() { return active; }
    public Rectangle getHitbox() { return hitbox; }
}
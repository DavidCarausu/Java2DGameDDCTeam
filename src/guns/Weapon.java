package guns;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

public class Weapon {
    private Rectangle hitbox;
    private int damage;
    private boolean isActive;
    private int cooldown;
    private final int MAX_COOLDOWN = 30; // Cadre între atacuri
    private float angle; // Unghiul de orientare

    public Weapon(int x, int y, int width, int height, int damage) {
        this.hitbox = new Rectangle(x, y, width, height);
        this.damage = damage;
        this.isActive = false;
        this.cooldown = 0;
        this.angle = 0;
    }

    public void update(int playerX, int playerY, float angle) {
        // Actualizează poziția relativ la jucător
        hitbox.x = playerX;
        hitbox.y = playerY;
        this.angle = angle;

        // Rotire hitbox (opțional, pentru arme orientate)
        // Poți implementa logica de rotire aici

        if (cooldown > 0) {
            cooldown--;
        }
    }

    public void attack() {
        if (cooldown <= 0) {
            isActive = true;
            cooldown = MAX_COOLDOWN;

            // Aici poți adăuga efecte sonore/animații
        }
    }

//    public void checkHit(Enemy enemy) {
//        if (isActive && hitbox.intersects(enemy.getHitbox())) {
//            enemy.takeDamage(damage);
//            isActive = false; // Dezactivează după lovitură
//        }
//    }

    public void render(Graphics2D g) {
        if (isActive) {
            g.setColor(new Color(255, 0, 0, 150)); // Roșu semitransparent
            g.fill(hitbox);

            // Desenează outline
            g.setColor(Color.RED);
            g.draw(hitbox);
        }
    }

    // Getters și Setters
    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
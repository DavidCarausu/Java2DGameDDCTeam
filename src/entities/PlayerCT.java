package entities;

import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.ct.Constants.*;
import static utils.ct.Constants.PlayerConstants.*;
import static utils.ct.GameCT.*;

public class PlayerCT {

    //Entity
    public int x, y;
    protected int width, height;
    protected Rectangle hitbox;


    //Player
    protected BufferedImage[][] animations;
    protected BufferedImage headImage;   // cap
    protected BufferedImage[][] handImage;   // mana
    protected int aniTick, aniIndex, aniSpeed = 10;
    protected int playerAction = IDLE;
    protected boolean moving = false;
    protected boolean left, right, up, down, jump;
    protected float playerSpeed = 2.7f;  //!!!!!!!! ASTA PE int RAMANE !!!!! la calcule conversie int-float probleme mari
    protected double angle = 0;  // unghi de rotatie
    protected int idleCooldown = 0; // cooldown pentru a trece la idling
    protected int stopCooldown = 0;
    protected float mouseX, mouseY;  // coordonatele mouse-ului
    protected int lvlData[][];

    // hitbox
    protected int xHitboxDim = (int) (54 * SCALE); // de aici schimbi
    protected int yHitboxDim = (int) (113 * SCALE);
    protected float hitboxOffsetX = 18 * SCALE;
    protected float hitboxOffsetY = 101 * SCALE;

    //mirror
    protected boolean facingRight = true;

    // jumping / gravity
    protected float airSpeed = 0f;
    protected float gravity = 0.04f * SCALE;
    protected float jumpSpeed = -3.25f * SCALE;
    protected float fallSpeedAfterCollision = 0.5f * SCALE;
    protected boolean inAir = false;


    public static int l1x=15830,l1y=750,l2x=1930,l2y=0,l3x=3923,l3y=1034;

}

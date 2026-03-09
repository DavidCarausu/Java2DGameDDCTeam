package entities;

import Main.Game;
import utils.HelpMethods;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.GetSpriteAmmount;

import static utils.ct.Constants.PlayerConstants.*;
import static utils.ct.GameCT.*;

public class Player extends Entity {
    int xLevelOffset; int yLevelOffset;

    public Player(int x, int y) {
        super(x, y, TILES_SIZE, TILES_SIZE);
        loadAnimations();
        loadHeadAnimations();  //  imaginea capului
        loadHandAnimations();  // imaginea mainii/armei
        initHitbox(x, y, xHitboxDim, yHitboxDim);

    }


    public void update() {
        updatePos();
        updateHitbox();
        setAnimation();
        updateAimingAngle();
        updateAnimationTic();

    }
    // unghi
    private void updateAimingAngle() {
        double deltaX = mouseX - (x  - xLevelOffset + 44);  // diferența pe axa X
        double deltaY = mouseY - (y  - yLevelOffset+ 64);  // diferența pe axa Y
        angle = Math.atan2(deltaY, deltaX);  // Calculam unghiul in radiani
        facingRight = deltaX >= 0;


        if (!facingRight) {
            angle = Math.atan2(-deltaY, -deltaX); // oglindim unghiul (efectiv rotim invers)

        }
    }

    public void updateMousePosition(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }


    public void render(Graphics g, int xLevelOffset, int yLevelOffset) {
        this.xLevelOffset = xLevelOffset;
        this.yLevelOffset = yLevelOffset;
        g.setColor(Color.RED);
        g.drawRect(hitbox.x - xLevelOffset, hitbox.y - yLevelOffset, hitbox.width, hitbox.height);

        int xCorp = (int) (hitbox.x - xLevelOffset - xHitboxDim + hitboxOffsetX);
        int yCorp = (int) (hitbox.y - yLevelOffset - yHitboxDim + hitboxOffsetY);
        if (animations != null && animations.length > 0 && animations[playerAction] != null && animations[playerAction].length > aniIndex) {
            if (facingRight) {
                g.drawImage(animations[playerAction][aniIndex], xCorp, yCorp, 128, 128, null);
            } else {
                g.drawImage(animations[playerAction][aniIndex], xCorp + 128, yCorp, -128, 128, null);
            }
           // drawHitbox(g);
        }

        Graphics2D g2d = (Graphics2D) g;
//das
        // capul (se rotește în jurul corpului)
        if (headImage != null) {
            int headPivotX = (int) (xCorp + 60);  // centru corp
            int headPivotY = (int) (yCorp + 50);  // centru corp
            int headX = (int) (xCorp + 2); // poziția imaginii capului
            int headY = (int) (yCorp - 15);            // ajustare verticală

            g2d.rotate(angle, headPivotX, headPivotY);
            if (facingRight) {
                g2d.drawImage(headImage, headX, headY, 128, 128, null);
            } else {
                g2d.drawImage(headImage, headX + 128, headY, -128, 128, null);
            }
            g2d.rotate(-angle, headPivotX, headPivotY);
        }

        // MÂNĂ - pivot relativ pe corp, dar în alt punct
        if (handImage != null) {
            int handX, handY, handPivotX, handPivotY;

            if (facingRight) {
                handX = (int) (xCorp - 30);
                handY = (int) (yCorp - 20);
                handPivotX = (int) (xCorp + 35);
                handPivotY = (int) (yCorp + 65);

                g2d.rotate(angle, handPivotX, handPivotY);
                g2d.drawImage(handImage[0][0], handX, handY, 128, 128, null);
                g2d.rotate(-angle, handPivotX, handPivotY);

            } else {
                handX = (int) (xCorp + 30); // invers față de varianta normală
                handY = (int) (yCorp - 20);
                handPivotX = (int) (xCorp + 93); // 35 + (128 - 2*35) = inversul punctului inițial
                handPivotY = (int) (yCorp + 65);

                g2d.rotate(angle, handPivotX, handPivotY);
                g2d.drawImage(handImage[0][0], handX + 128, handY, -128, 128, null); // mirror
                g2d.rotate(-angle, handPivotX, handPivotY);
            }
        }
    }



    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
            idleCooldown = 0;
        } else {
            playerAction = IDLE;
            moving = false;

        }
    }

    private void updatePos() {
        moving = false;
        if (jump)
            jump();
        if (!left && !right && !inAir) {
            return;
        }
        if (!inAir) {
            if (!HelpMethods.IsEntityNotInAir(hitbox, lvlData))
                inAir = true;
        }
        int xSpeed = 0;
        int ySpeed = 0;


        if (left) {
            if(!HelpMethods.IsStepLeft(hitbox, lvlData)) {
                moving = true;
                xSpeed -= playerSpeed;
            }
            else {
              //  System.out.println("Step");
                jump2();
                moving = true;
                xSpeed -= playerSpeed;
            }}


        if (right) {
            if (!HelpMethods.IsStepRight(hitbox, lvlData)) {
                moving = true;
                xSpeed += playerSpeed;
                System.out.println(x + " " + y);
            }
            else
            {
                //System.out.println("Step");
                jump2();
                moving = true;
                xSpeed += playerSpeed;
            }
        }

        if (inAir) {
            if (HelpMethods.CanMoveHere(hitbox.x, (int) (hitbox.y + airSpeed), hitbox.width, hitbox.height, lvlData, levels.LevelManager.getCurrentLevel().getLiftVector())) {
                y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
               // hitbox.y = HelpMethods.GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
                moving = true;
            }
        } else {
            updateXPos(xSpeed);
        }


    }

    private void jump2() {while(!inAir)

        inAir = true;
        airSpeed = jumpSpeed/4.3f;
    }


    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }
//
//    private void updateXPos(int xSpeed) {
//        if (HelpMethods.CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
//            x += xSpeed;
//        } else {
//            x = HelpMethods.GetEntityXPosNextToWall(hitbox, xSpeed);
//        }
//    }
private void updateXPos(int xSpeed) {
    if (HelpMethods.CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
        x += xSpeed;
    }
}

    private void updateAnimationTic() {
        aniTick++;
        if (aniTick >= (playerAction == IDLE ? aniSpeed * 2 : aniSpeed)) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmmount(playerAction)) {
                aniIndex = 0;
                if (!moving) {
                    idleCooldown = 0;
                }
            }
        }
    }

    private void loadFULLAnimations() {
        BufferedImage img = LoadSave.GetFULLPlayerAtlas();
        this.animations = new BufferedImage[4][15];
        // (1)   idle normal[0][0-3] +idle mirror[0][4-7] + jump[0][8-11]
        // (2)     animatie normala[1][0-14]
        // (3)      animatie mirror[2][0-14]
        // (4)     mana normala[3][0-2] + mana mirror[3][3-5] + cap[3][6] + cap mirror[3][7]+ ump Mirror[0][8-11]


        // (1)
        {
            int i;
            for (i = 0; i < 4; ++i) {
                this.animations[0][i] = img.getSubimage(i * TILES_SIZE, 0, TILES_SIZE, TILES_SIZE);
            }
            for (int j = 14; j > 10; --j) {
                this.animations[0][i] = img.getSubimage(j * TILES_SIZE, 3 * TILES_SIZE, TILES_SIZE, TILES_SIZE);
                ++i;
            }
            for (int j = 0; j < 4; ++j) {
                this.animations[0][i] = img.getSubimage(j * TILES_SIZE, 2 * TILES_SIZE, TILES_SIZE, TILES_SIZE);
                ++i;
            }

        }
        // (2)
        {
            for (int i = 0; i < 15; ++i) {
                this.animations[1][i] = img.getSubimage(i * TILES_SIZE, 1 * TILES_SIZE, TILES_SIZE, TILES_SIZE);
            }
        }
        // (3)
        {
            for (int i = 14; i >= 0; --i) {
                this.animations[2][i] = img.getSubimage(i * TILES_SIZE, 4 * TILES_SIZE, TILES_SIZE, TILES_SIZE);
            }
        }
        // (4)
        {
            int i = 0;
            this.animations[3][i] = img.getSubimage(2 * TILES_SIZE, 4 * TILES_SIZE, TILES_SIZE, TILES_SIZE);
            ++i;
            for(int j=0;j<3;j++)
            {
                this.animations[3][i] = img.getSubimage(3*TILES_SIZE, (4+j)*TILES_SIZE, TILES_SIZE, TILES_SIZE);
                ++i;
            }
            this.animations[3][i] = img.getSubimage(2 * TILES_SIZE, 7 * TILES_SIZE, TILES_SIZE, TILES_SIZE);
            ++i;


            for (int j = 0; j <2; ++j) {
                this.animations[0][i] = img.getSubimage(3 * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE);
                ++i;
            }
            for(int j=14;j>10;j--)
            {
                this.animations[0][i] = img.getSubimage(5 * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE);
                ++i;
            }
        }


    }
    public void moveY(int dy) {
        this.y += dy;
        this.hitbox.y += dy;
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetPlayerAtlas();
        this.animations = new BufferedImage[2][];
        this.animations[0] = new BufferedImage[4];
        this.animations[1] = new BufferedImage[15];

        for (int i = 0; i < 4; ++i) {
            this.animations[0][i] = img.getSubimage(i * 128, 0, 128, 128);
        }

        for (int i = 0; i < 15; ++i) {
            this.animations[1][i] = img.getSubimage(i * 128, 128, 128, 128);
        }

    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }


    private void loadHeadAnimations() {
        this.headImage = LoadSave.GetPlayerAtlasHead();
    }

    private void loadHandAnimations() {
        BufferedImage img = LoadSave.GetPlayerAtlasHand();
        this.handImage = new BufferedImage[2][];
        this.handImage[0] = new BufferedImage[4];
        this.handImage[1] = new BufferedImage[15];

        this.handImage[0][0] = img.getSubimage(0 * 128, 0, 128, 128);

        for (int i = 0; i < 2; ++i) {
            this.handImage[1][i] = img.getSubimage(i * 128, 128, 128, 128);
        }
    }




    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void resetDirBooleans() {
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
    }

    public void stopMoving() {
        this.moving = false;
    }
    public void setX(int x) {
        this.x = x;
        updateHitbox();
    }

    public void setY(int y) {
        this.y = y;
        updateHitbox();
    }

    }

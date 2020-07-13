package top.candy.tank;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {
    private static final int SPEED = 10;

    public static int WIDTH = ResourceMgr.bulletD.getWidth(),HIGHT = ResourceMgr.bulletD.getWidth();

    private int x,y;
    private Dir dir;

    private TankFrame tf;

    private boolean living = true;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!living){
            tf.bullets.remove(this);
        }

        switch (dir){
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                WIDTH = ResourceMgr.bulletR.getWidth();
                HIGHT = ResourceMgr.bulletR.getHeight();
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                WIDTH = ResourceMgr.bulletL.getWidth();
                HIGHT = ResourceMgr.bulletL.getHeight();
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                WIDTH = ResourceMgr.bulletD.getWidth();
                HIGHT = ResourceMgr.bulletD.getHeight();
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                WIDTH = ResourceMgr.bulletU.getWidth();
                HIGHT = ResourceMgr.bulletU.getHeight();
                break;
        }

        move();
    }

    private void move() {
        switch (dir){
            case RIGHT: x += SPEED;break;
            case LEFT: x -= SPEED;break;
            case DOWN:  y += SPEED;break;
            case UP: y -= SPEED;break;
        }

        if(x <0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGTH){
            living=false;
        }
    }
}

package top.candy.tank;

import java.awt.*;

public class Tank {

    private int x,y;
    private Dir dir = Dir.UP;
    private static final int SPEED = 5;

    public static int WIDTH ,HIGHT;

    private boolean moving = false;

    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        switch (dir){
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                WIDTH = ResourceMgr.tankR.getWidth();
                HIGHT = ResourceMgr.tankR.getHeight();
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                WIDTH = ResourceMgr.tankL.getWidth();
                HIGHT = ResourceMgr.tankL.getHeight();
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                WIDTH = ResourceMgr.tankD.getWidth();
                HIGHT = ResourceMgr.tankD.getHeight();
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                WIDTH = ResourceMgr.tankU.getWidth();
                HIGHT = ResourceMgr.tankU.getHeight();
                break;
        }

        move();
    }

    private void move() {
        if(!moving) return;
        switch (dir){
            case RIGHT: x += SPEED;break;
            case LEFT: x -= SPEED;break;
            case DOWN: y += SPEED;break;
            case UP: y -= SPEED;break;
        }
    }

    public void fire() {

        int bX = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = this.y+Tank.HIGHT/2-Bullet.HIGHT/2;


        tf.bullets.add(new Bullet(bX,bY,this.dir,this.tf));
    }
}

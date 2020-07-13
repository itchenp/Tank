package top.candy.tank;

import java.awt.*;

public class Tank {

    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;

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
            case RIGHT:g.drawImage(ResourceMgr.tankR,x,y,null);break;
            case LEFT:g.drawImage(ResourceMgr.tankL,x,y,null);break;
            case DOWN:g.drawImage(ResourceMgr.tankD,x,y,null);break;
            case UP:g.drawImage(ResourceMgr.tankU,x,y,null);break;
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
        tf.bullets.add(new Bullet(this.x+25,this.y,this.dir,this.tf));
    }
}

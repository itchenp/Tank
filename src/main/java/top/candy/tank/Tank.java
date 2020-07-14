package top.candy.tank;

import java.awt.*;
import java.util.Random;


public class Tank {

    private int x,y;
    private Dir dir = Dir.UP;
    private static final int SPEED = 2;

    public static int WIDTH ,HIGHT;
    private boolean moving = false;
    private TankFrame tf = null;
    private boolean living = true;
    private Group group = Group.BAD;
    private Rectangle tankRec ;

    private Random random = new Random();

    public Tank(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        changeWidthAndHigth();

        tankRec = new Rectangle(x,y,WIDTH,HIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getTankRec() {
        return tankRec;
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

    private void changeWidthAndHigth(){
        switch (dir){
            case RIGHT:
                WIDTH = ResourceMgr.tankR.getWidth();
                HIGHT = ResourceMgr.tankR.getHeight();
                break;
            case LEFT:
                WIDTH = ResourceMgr.tankL.getWidth();
                HIGHT = ResourceMgr.tankL.getHeight();
                break;
            case DOWN:
                WIDTH = ResourceMgr.tankD.getWidth();
                HIGHT = ResourceMgr.tankD.getHeight();
                break;
            case UP:
                WIDTH = ResourceMgr.tankU.getWidth();
                HIGHT = ResourceMgr.tankU.getHeight();
                break;
        }
    }

    public void paint(Graphics g) {
        if(!living){
            tf.tanks.remove(this);
        }

        switch (dir){
            case RIGHT: g.drawImage(ResourceMgr.tankR,x,y,null);break;
            case LEFT: g.drawImage(ResourceMgr.tankL,x,y,null);break;
            case DOWN: g.drawImage(ResourceMgr.tankD,x,y,null);break;
            case UP: g.drawImage(ResourceMgr.tankU,x,y,null);break;
        }

        changeWidthAndHigth();

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

        tankRec.setBounds(x,y,WIDTH,HIGHT);

        if(random.nextInt(10)>8) fire();
    }

    public void fire() {

        int bX = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = this.y+Tank.HIGHT/2-Bullet.HIGHT/2;


        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
    }

    public void die() {
        this.living = false;
        tf.explode = new Explode(this.x,this.y,true,this.tf);
    }
}

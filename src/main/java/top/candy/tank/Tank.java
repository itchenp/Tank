package top.candy.tank;

import java.awt.*;
import java.util.Random;


public class Tank {

    private int x,y;
    private Dir dir = Dir.UP;
    private static final int SPEED = 10;

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
                WIDTH = ResourceMgr.goodTankR.getWidth();
                HIGHT = ResourceMgr.goodTankR.getHeight();
                break;
            case LEFT:
                WIDTH = ResourceMgr.goodTankL.getWidth();
                HIGHT = ResourceMgr.goodTankL.getHeight();
                break;
            case DOWN:
                WIDTH = ResourceMgr.goodTankD.getWidth();
                HIGHT = ResourceMgr.goodTankD.getHeight();
                break;
            case UP:
                WIDTH = ResourceMgr.goodTankU.getWidth();
                HIGHT = ResourceMgr.goodTankU.getHeight();
                break;
        }
    }

    public void paint(Graphics g) {
        if(!living){
            tf.tanks.remove(this);
        }

        switch (dir){
            case RIGHT: g.drawImage(ResourceMgr.goodTankR,x,y,null);break;
            case LEFT: g.drawImage(ResourceMgr.goodTankL,x,y,null);break;
            case DOWN: g.drawImage(ResourceMgr.goodTankD,x,y,null);break;
            case UP: g.drawImage(ResourceMgr.goodTankU,x,y,null);break;
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
        tf.explodes.add(new Explode(this.x,this.y,this.tf));
    }
}

package top.candy.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Tank {

    private int x,y;
    private Dir dir = Dir.UP;
    private static final int SPEED = Integer.parseInt((String)PropertiesMgr.get("tankSpeed"));

    public static int WIDTH ,HIGHT;
    private boolean moving = true;
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
        BufferedImage tankBufferedImage;
        switch (dir){
            case RIGHT:
                tankBufferedImage = this.group == Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR;
                WIDTH = tankBufferedImage.getWidth();
                HIGHT = tankBufferedImage.getHeight();
                break;
            case LEFT:
                tankBufferedImage = this.group == Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL;
                WIDTH = tankBufferedImage.getWidth();
                HIGHT = tankBufferedImage.getHeight();
                break;
            case DOWN:
                tankBufferedImage = this.group == Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD;
                WIDTH = tankBufferedImage.getWidth();
                HIGHT = tankBufferedImage.getHeight();
                break;
            case UP:
                tankBufferedImage = this.group == Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU;
                WIDTH = tankBufferedImage.getWidth();
                HIGHT = tankBufferedImage.getHeight();
                break;
        }
    }

    public void paint(Graphics g) {
        if(!living){
            tf.tanks.remove(this);
        }

        switch (dir){
            case RIGHT: g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);break;
            case LEFT: g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);break;
            case DOWN: g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);break;
            case UP: g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);break;
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

        if(this.group==Group.BAD && random.nextInt(100)>95) {
            fire();
        }

        if(this.group==Group.BAD && random.nextInt(100)>95) {
            randomDir();
        }

        boundsCheck();

        tankRec.setBounds(x,y,WIDTH,HIGHT);
    }

    private void boundsCheck() {
        if(x<2) {
            x=2;
            this.dir = Dir.RIGHT;
        }
        if(y<28) {
            y = 28;
            this.dir = Dir.DOWN;
        }
        if(x> TankFrame.GAME_WIDTH-Tank.WIDTH -2) {
            x = TankFrame.GAME_WIDTH -Tank.WIDTH -2;
            this.dir = Dir.LEFT;
        }
        if(y> TankFrame.GAME_HEIGTH-Tank.HIGHT -2) {
            y = TankFrame.GAME_HEIGTH -Tank.HIGHT -2;
            this.dir = Dir.UP;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {

        int bX = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = this.y+Tank.HIGHT/2-Bullet.HIGHT/2;


        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tf));

        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

    }

    public void die() {
        this.living = false;

        int eX = this.x+Tank.WIDTH/2-Explode.WIDTH/2;
        int eY = this.y+Tank.HIGHT/2-Explode.HIGHT/2;

        tf.explodes.add(new Explode(eX,eY,this.tf));
    }
}

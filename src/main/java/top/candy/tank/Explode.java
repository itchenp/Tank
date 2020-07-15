package top.candy.tank;

import java.awt.*;

public class Explode {


    public static int WIDTH = ResourceMgr.explodes[0].getWidth(),HIGHT = ResourceMgr.explodes[0].getWidth();

    private int x,y;
    private TankFrame tf;
    private int step = 0;


    public Explode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();

    }

    public void paint(Graphics g) {

        if(step >= ResourceMgr.explodes.length) {
            tf.explodes.remove(this);
            return;
        }

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

    }



}

package top.candy.tank;

import java.awt.*;

public class Explode {


    public static int WIDTH = ResourceMgr.explodes[0].getWidth(),HIGHT = ResourceMgr.explodes[0].getWidth();

    private int x,y;
    private TankFrame tf;
    private int step = 0;
    private boolean explode = false;


    public Explode(int x, int y,boolean explode,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.explode = explode;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!explode) return;

        if(step >= ResourceMgr.explodes.length) {
            step = 0;
            explode = false;
            return;
        }

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

    }



}

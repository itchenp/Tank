package top.candy.tank;

public class Mian {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();



        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(20+i*100,80,Dir.DOWN,Group.BAD,tankFrame));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();


        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}

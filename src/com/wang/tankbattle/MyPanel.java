package com.wang.tankbattle;

import com.wang.tank.EnemyTanks;
import com.wang.tank.Hero;
import com.wang.tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable{
    Hero hero;
    Vector<EnemyTanks> enemyTanks = new Vector<>();
    Vector<Node> nodes = new Vector<>();
    int enemyTanksSize = 7;
    Vector<Boom> booms = new Vector<>();

    Image image1;
    Image image2;
    Image image3;
    public MyPanel( String key){
        //将enemytanks传给Recoder
        File file = new File(Recorder.getSecPath());
        if (file.exists()){
            Recorder.setEnemyTanks(enemyTanks);
        }else{
            System.out.println("上一句游戏不存在");
            key = "1";
        }

        hero = new Hero(800,600);
        hero.setSpeed(5);

        switch (key){
            case "1":
                for (int i = 0; i < enemyTanksSize; i++){
                    EnemyTanks enemyTank = new EnemyTanks(100 * (i + 1), 0);
                    enemyTank.setDirect(2);
                    enemyTanks.add(enemyTank);
                    enemyTank.setEnemyTank(enemyTanks);
                    //启动坦克线程
                    new Thread(enemyTank).start();
                    Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+65,enemyTank.getDirect());
                    enemyTank.getShots().add(shot);
                    new Thread(shot).start();
                }
                break;
            case "2":
                nodes = Recorder.loadNodeEnemyTank();
                for (int i = 0; i < nodes.size(); i++){
                    Node node = nodes.get(i);
                    EnemyTanks enemyTank = new EnemyTanks(node.getX(), node.getY());
                    enemyTank.setDirect(node.getDirect());
                    enemyTanks.add(enemyTank);
                    enemyTank.setEnemyTank(enemyTanks);
                    //启动坦克线程
                    new Thread(enemyTank).start();
                    Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+65,enemyTank.getDirect());
                    enemyTank.getShots().add(shot);
                    new Thread(shot).start();

                }
                break;
            default:
                System.out.println("输入有误，请重新输入！");
        }


        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image3.png"));
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,1000,750);
        //添加当前击毁坦克数量信息
        showInfo(g);
        if (hero != null && hero.isLive()) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }
        //绘制多颗子弹
        for (int i = 0; i < hero.getShots().size(); i++) {
            Shot shot = hero.getShots().get(i);
            if (shot != null && shot.isLive()){
                g.fillOval(shot.getX(),shot.getY(),5,5);
            }else {
                hero.getShots().remove(shot);
            }
        }
        //如果集合中有boom对象，就画出
        for (int i = 0; i < booms.size(); i++) {
            Boom boom = booms.get(i);
            if (boom.getLife() > 6) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.drawImage(image1, boom.getX(), boom.getY(), 60, 60, this);
            } else if (boom.getLife() > 3) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.drawImage(image2, boom.getX(), boom.getY(), 60, 60, this);
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.drawImage(image3, boom.getX(), boom.getY(), 60, 60, this);
            }
            //让炸弹生命值减少
            boom.lifeDown();
            System.out.println(boom.getLife());
            //如果boom生命值为0，就从集合中删除
            if (boom.getLife() == 0) {
                booms.remove(boom);
            }
        }
        //画出敌人坦克
        for (int i = 0;i < enemyTanks.size(); i++){
            EnemyTanks enemyTank = this.enemyTanks.get(i);
            if (enemyTank.isLive()){
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
                //敌人坦克的子弹
                for (int j = 0; j < enemyTank.getShots().size(); j++){
                    Shot shot = enemyTank.getShots().get(j);
                    if (shot.isLive()){
                        g.fillOval(shot.getX(),shot.getY(),5,5);
                    }else{
                        enemyTank.getShots().remove(shot);
                    }
                }
            }
        }
    }

    //显示我方击毁敌人坦克数量
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getEnemyTankNum()+"",1080,100);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            if (hero.getDirect() != 0){
                hero.setDirect(0);
                System.out.println("开始向上移动");
            }else{
                hero.moveUp();
                System.out.println("向上移动一个单位");
            }
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            if (hero.getDirect() != 1){
                hero.setDirect(1);
                System.out.println("开始向右移动");
            }else{
                hero.moveRight();
                System.out.println("向右移动一个单位");
            }
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            if(hero.getDirect() != 2){
                hero.setDirect(2);
                System.out.println("开始向下移动");
            }else{
                hero.moveDown();
                System.out.println("向下移动一个单位");
            }
        }else if(e.getKeyCode() == KeyEvent.VK_A){
            if (hero.getDirect() != 3){
                hero.setDirect(3);
                System.out.println("开始向下移动");
            }else{
                hero.moveLeft();
                System.out.println("向左移动一个单位");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_J){
            hero.shot();
        }
        this.repaint();
    }


    public void drawTank(int x,int y,Graphics g,int direct,int type){
        //设置坦克颜色
        switch(type){
            case 0://自己的坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        //设置坦克方向
        switch (direct){
            //向上
            case 0:
                g.fill3DRect(x,y,10,60,false);//坦克左轮
                g.fill3DRect(x+30,y,10,60,false);//坦克右轮
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.drawOval(x+10,y+20,20,20);//圆形盖子
                g.drawLine(x+20,y-5,x+20,y+30);//炮筒
                break;
            //向右
            case 1:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.drawOval(x+20,y+10,20,20);
                g.drawLine(x+65,y+20,x+30,y+20);
                break;
            //向下
            case 2:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.drawOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+65,x+20,y+30);
                break;
            //向左
            case 3:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.drawOval(x+20,y+10,20,20);
                g.drawLine(x-5,y+20,x+30,y+20);
                break;
        }
        this.repaint();
    }

    //射击敌人的坦克
    public void hitEnemyTanks(){
        for (int i = 0; i < hero.getShots().size(); i++) {
            //得到自己坦克的子弹
            Shot shot = hero.getShots().get(i);
            //判断子弹是否失效
            if (shot != null && shot.isLive()){
                //遍历敌人的坦克
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTanks enemyTank = this.enemyTanks.get(j);
                    hitTank(shot,enemyTank);
                }
            }
        }
    }
    //击中坦克
    public void hitTank(Shot s, Tank tank){
        if (s.isLive() && tank.isLive()){
            switch (tank.getDirect()){
                case 0:
                case 2:
                    if (s.getX() > tank.getX() && s.getX() < tank.getX()+40 &&
                    s.getY() > tank.getY() && s.getY() < tank.getY()+60){
                        tank.setLive(false);
                        s.setLive(false);
                        //判断是否敌人坦克被击毁
                        if (tank instanceof EnemyTanks){
                            Recorder.addEnemyTankNum();
                        }
                        //创建boom对象，加到boom集合
                        Boom boom = new Boom(tank.getX(), tank.getY());
                        booms.add(boom);
                    }
                    break;
                case 1:
                case 3:
                    if (s.getX() > tank.getX() && s.getX() < tank.getX()+60 &&
                            s.getY() > tank.getY() && s.getY() < tank.getY()+40){
                        tank.setLive(false);
                        s.setLive(false);
                        //判断是否敌人坦克被击毁
                        if (tank instanceof EnemyTanks){
                            Recorder.addEnemyTankNum();
                        }
                        Boom boom = new Boom(tank.getX(), tank.getY());
                        booms.add(boom);
                    }
                    break;
            }
        }
    }

    //射击自己的坦克
    public void hitHero(){
        //遍历敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTanks enemyTank = this.enemyTanks.get(i);
            //遍历敌人的子弹
            for (int j = 0; j < enemyTank.getShots().size(); j++) {
                Shot shot = enemyTank.getShots().get(j);
                if (shot.isLive() && hero.isLive()) {
                    hitTank(shot, hero);
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //击中敌人坦克
            hitEnemyTanks();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //击中自己坦克
            hitHero();
            this.repaint();
        }
    }
}

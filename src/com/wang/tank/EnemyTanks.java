package com.wang.tank;

import com.wang.tankbattle.Shot;

import java.util.Vector;

public class EnemyTanks extends Tank implements Runnable{
    private Vector<Shot> shots = new Vector<>();
    private Vector<EnemyTanks> enemyTanks = new Vector<>();
    private boolean isLive = true;
    public EnemyTanks(int x, int y) {
        super(x, y);
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setEnemyTank(Vector<EnemyTanks> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    /*@SuppressWarnings("all")
    public boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //得到一个坦克
                    EnemyTanks enemyTank = this.enemyTanks.get(i);
                    //排除掉当前坦克
                    if (enemyTank != this && enemyTank.isLive) {
                        //当敌人坦克向上或者向下时
                       //进行碰撞判断
                        //分析：发生碰撞时坦克的坐标
                        //x:getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                        //y:getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克左上坐标
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {

                                return true;
                            }
                            //当前坦克右上坐标
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() +40 <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {
                            }
                        }
                        //当敌人坦克向左或者向右时
                        //敌人坦克坐标：
                        //x:[enemyTank.getX(),enemyTank.getX() + 60]
                        //y:[enemyTamk.getY(),enemyTank.getY() + 40]
                       if (enemyTank.getDirect() == 1 && enemyTank.getDirect() == 3) {
                            //当前坦克左上坐标
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {

                                return true;
                            }
                            //当前坦克右上坐标
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40<= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //得到一个坦克
                    EnemyTanks enemyTank = this.enemyTanks.get(i);
                    //排除掉当前坦克
                    if (enemyTank != this && enemyTank.isLive) {
                        //当敌人坦克向上或者向下时
                        //进行碰撞判断
                        //分析：发生碰撞时坦克的坐标
                        //x:getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                        //y:getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            ////当前坦克右上坐标：getX()+60,getY()
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60<= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {

                                return true;
                            }
                            //当前坦克右下坐标：getX()+60,getY()+40
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 40
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 60) {
                                return true;

                            }
                        }
                        //当敌人坦克向左或者向右时
                        //敌人坦克坐标：
                        //x:[enemyTank.getX(),enemyTank.getX() + 60]
                        //y:[enemyTamk.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 && enemyTank.getDirect() == 3) {
                            //当前坦克右上坐标：getX()+60,getY()
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60<= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {

                                return true;
                            }
                            //当前坦克右下坐标 getX()+60,getY()+40
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 60
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //得到一个坦克
                    EnemyTanks enemyTank = this.enemyTanks.get(i);
                    //排除掉当前坦克
                    if (enemyTank != this && enemyTank.isLive) {
                        //当敌人坦克向上或者向下时
                        //进行碰撞判断
                        //分析：发生碰撞时坦克的坐标
                        //x:getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                        //y:getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            ////当前坦克左下坐标：getX(),getY()+60
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() + 60 >= enemyTank.getY()
                                    && getY() + 60<= enemyTank.getY() + 60) {

                                return true;
                            }
                            //当前坦克右下坐标：getX()+40,getY()+60
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 40
                                    && getY() + 60 >= enemyTank.getY()
                                    && getY() + 60 <= enemyTank.getY() + 40) {
                                return true;

                            }
                        }
                        //当敌人坦克向左或者向右时
                        //敌人坦克坐标：
                        //x:[enemyTank.getX(),enemyTank.getX() + 60]
                        //y:[enemyTamk.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 && enemyTank.getDirect() == 3) {
                            //当前坦克右下坐标：getX(),getY()+60
                            if (getX()  >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() + 60>= enemyTank.getY()
                                    && getY() + 60<= enemyTank.getY() + 40) {

                                return true;
                            }
                            //当前坦克右下坐标 getX()+40,getY()+60
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() + 60 >= enemyTank.getY()
                                    && getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //得到一个坦克
                    EnemyTanks enemyTank = this.enemyTanks.get(i);
                    //排除掉当前坦克
                    if (enemyTank != this && enemyTank.isLive) {
                        //当敌人坦克向上或者向下时
                        //进行碰撞判断
                        //分析：发生碰撞时坦克的坐标
                        //x:getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                        //y:getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            ////当前坦克左上坐标：getX(),getY()
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {

                                return true;
                            }
                            //当前坦克左下坐标：getX(),getY()+40
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                        //当敌人坦克向左或者向右时
                        //敌人坦克坐标：
                        //x:[enemyTank.getX(),enemyTank.getX() + 60]
                        //y:[enemyTamk.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 && enemyTank.getDirect() == 3) {
                            //当前坦克左上坐标：getX(),getY()
                            if (getX()  >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {

                                return true;
                            }
                            //当前坦克左下坐标 getX(),getY()+40
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }*/

    //编写方法，判断当前的这个敌人坦克，是否和 enemyTanks 中的其他坦克发生的重叠或者碰撞
    public boolean isTouchEnemyTank() {
        //判断当前敌人坦克(this) 方向
        switch (this.getDirect()) {
            case 0: //上
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTanks enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }

                }
                break;
            case 1: //右
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTanks enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 右上角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 右上角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //下
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTanks enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //左
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTanks enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY() ]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY() ]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true){

            if (isLive && shots.size() < 3){
                Shot s = null;
                switch (getDirect()){
                    case 0:
                        s = new Shot(getX() + 20,getY() +5,0);
                        break;
                    case 1:
                        s = new Shot(getX() + 65,getY() + 20,1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20,getY() + 65,2);
                        break;
                    case 3:
                        s = new Shot(getX() - 5,getY() + 20,3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }

            switch (getDirect()){
                case 0://上
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!isTouchEnemyTank()){
                            moveUp();
                        }

                    }
                    break;
                case 1://右
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!isTouchEnemyTank()){
                            moveRight();
                        }
                    }
                    break;
                case 2://下
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!isTouchEnemyTank()){
                            moveDown();
                        }
                    }
                    break;
                case 3://左
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!isTouchEnemyTank()){
                            moveLeft();
                        }
                    }
                    break;
            }
            setDirect((int)(Math.random() * 4));

        }
    }
}

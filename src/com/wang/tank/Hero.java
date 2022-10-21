package com.wang.tank;

import com.wang.tankbattle.Shot;

import java.util.Vector;

public class Hero extends Tank {
    private Shot shot = null;
    public Hero(int x, int y) {
        super(x, y);
    }
    private Vector<Shot> shots = new Vector<>();
    public void shot(){
        if (shots.size() == 10){
            return;
        }
        switch (getDirect()){
            case 0:
                shot = new Shot(getX()+18,getY()-5,0);
                break;
            case 1:
                shot = new Shot(getX()+65,getY()+18,1);
                break;
            case 2:
                shot = new Shot(getX()+18,getY()+65,2);
                break;
            case 3:
                shot = new Shot(getX()-5,getY()+18,3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }

    public Shot getShot() {
        return shot;
    }

    public Vector<Shot> getShots() {
        return shots;
    }
}

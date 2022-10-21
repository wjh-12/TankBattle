package com.wang.tank;

public class Tank {
    private int x,y;
    private int direct;//0 上 ，1 右，2 下，3左
    private int speed = 3;
    private boolean isLive = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp(){
        if (y >= 10){
            y -= speed;
        }
    }
    public void moveRight(){
        if (x + 70 <= 1000){
            x += speed;
        }
    }
    public void moveDown(){
        if (y + 70 <= 750){
            y += speed;
        }
    }
    public void moveLeft(){
        if (x >= 10){
            x -= speed;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}

package com.wang.tankbattle;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame extends JFrame {
    private MyPanel mp;
    static String key;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("程序启动");
        new TankGame();
    }

    public TankGame(){
        System.out.println("输入1:开始新游戏\n输入2:继续上局游戏");
        while (true){
            key = scanner.next();
            if (key.equals("1") || key.equals("2")){
                break;
            }else {
                System.out.println("输入的内容不符合要求，请重新输入！");
            }
        }

        mp = new MyPanel(key);

        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);
        this.setSize(1300,900);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在Jframe 中增加相应关闭窗口处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}

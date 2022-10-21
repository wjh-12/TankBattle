package com.wang.tankbattle;

import com.wang.tank.EnemyTanks;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int enemyTankNum;
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static String secPath = "src\\myRecord.txt";
    private static Vector<EnemyTanks> enemyTanks;
    private static Vector<Node> nodes = new Vector<>();

    public static String getSecPath() {
        return secPath;
    }

    public static void setNodes(Vector<Node> nodes) {
        Recorder.nodes = nodes;
    }

    public static void setEnemyTanks(Vector<EnemyTanks> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getEnemyTankNum() {
        return enemyTankNum;
    }

    public static void setEnemyTankNum(int enemyTankNum) {
        Recorder.enemyTankNum = enemyTankNum;
    }

    public static void addEnemyTankNum(){
        enemyTankNum++;
    }

    //增加一个方法，当游戏退出时，将enemyTankNum保存到文件
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(secPath));
            bw.write(enemyTankNum + "\r\n");
            if(enemyTanks.size() > 0){
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTanks enemyTank = Recorder.enemyTanks.get(i);
                    if (enemyTank.isLive()){
                        bw.write(enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect() + "\r\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Vector<Node> loadNodeEnemyTank() {
        try {
            br = new BufferedReader(new FileReader(secPath));
            enemyTankNum = Integer.parseInt(br.readLine());
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                System.out.println(node);
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

}

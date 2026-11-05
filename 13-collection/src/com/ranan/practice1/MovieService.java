package com.ranan.practice1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class MovieService {
    // 定义容器存放电影
    private static ArrayList<Movie> movies = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public void start(){
        // 准备cmd操作界面
        System.out.println("========电影信息操作系统========");
        System.out.println("| 1.上架某个电影              |");
        System.out.println("| 2.下架某个电影              |");
        System.out.println("| 3.查询某个电影              |");
        System.out.println("| 4.封杀某个明星              |");
        System.out.println("| 5.退出                    |");
        System.out.println("=============================");

        int choice = 0;
        while(true) {
            System.out.printf("请您输入操作命令：");
            try {
                choice = sc.nextInt();
                if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5)
                   continue;
            } catch (Exception e) {
                sc.next();
                System.out.println("输入不合法!");
                continue;
            }

            switch(choice){
                case 1:
                    // 上架
                    addMovie();
                    break;
                case 2:
                    // 下架
                    removeMovie();
                    break;
                case 3:
                    // 查询
                    queryMovie();
                    break;
                case 4:
                    removeAllMovieByActor();
                    // 封杀
                    break;
                case 5:
                    // 退出
                    return;
                default:
                    break;
            }
        }
    }

    // 上架
    private void addMovie(){
        String name;
        double score;
        String actor;
        double price;
        while(true) {
            System.out.printf("请您输入电影名：");
            name = sc.next();
            if (!name.equals("")) break;
            else System.out.println("输入不能为空");
        }
        while(true){
            System.out.printf("请您输入打分：");
            try {
                score = sc.nextDouble();
                if(score < 0) throw new IllegalArgumentException();
                else break;
            } catch (IllegalArgumentException e) {
                System.out.println("输入不合法");
            } catch (InputMismatchException e){
                sc.next();
                System.out.println("输入不合法");
            }
        }
        while(true) {
            System.out.printf("请您输入主演名：");
            actor = sc.next();
            if (actor != null) break;
        }
        while(true){
            System.out.printf("请您输入票价：");
            try {
                price = sc.nextDouble();
                if(price < 0) throw new IllegalArgumentException();
                else break;
            } catch (IllegalArgumentException e) {
                System.out.println("输入不合法");
            } catch (InputMismatchException e){
                sc.next();
                System.out.println("输入不合法");
            }
        }
        movies.add(new Movie(name,score,actor,price));
        System.out.println("已成功添加电影：" +
                "{" + name + "," +
                score + "," +
                actor + "," +
                price + "}");
    }

    // 通过名字查找电影
    private Movie queryMovieByName(String name){
        for(Movie m:movies){
            if(m.getName().equals(name)){
                return m;
            }
        }
        return null;
    }

    // 删除电影
    private void removeMovie(){
        String name;
        while(true) {
            System.out.printf("请您输入要删除的电影名：");
            name = sc.next();
            if (!name.equals(""))
                if(queryMovieByName(name) != null)
                    break;
                else
                    System.out.println("未查询到" + name);
            else
                System.out.println("不能输入为空");
        }

        movies.remove(queryMovieByName(name));
        System.out.println("已成功删除");
    }

    // 查询电影
    private void queryMovie(){
        String name;
        while(true) {
            System.out.printf("请您输入要删除的电影名：");
            name = sc.next();
            if (!name.equals(""))
                if(queryMovieByName(name) != null)
                    break;
                else
                    System.out.println("未查询到" + name);
            else
                System.out.println("不能输入为空");
        }

        Movie m = queryMovieByName(name);
        System.out.println("电影名：" + m.getName() + "\n" +
                "打分：" + m.getScore() + "\n" +
                "主演：" + m.getActor() + "\n" +
                "票价：" + m.getPrice());
        System.out.println("电影查询完成");
    }

    // 封杀艺人
    private void removeAllMovieByActor(){
        String actor;
        while(true) {
            System.out.printf("请您输入要封杀的艺名：");
            actor = sc.next();
            boolean hasActor = false;
            if (!actor.equals("")){
                for(Iterator<Movie> it = movies.iterator();it.hasNext();){
                    if(it.next().getActor().equals(actor)){
                        it.remove();
                        hasActor = true;
                    }
                }
                if(!hasActor){
                    System.out.println("未查询到该演员");
                    continue;
                }
                break;
            }
            else
                System.out.println("不能输入为空");
        }
        System.out.println("已成功封杀");
    }
}

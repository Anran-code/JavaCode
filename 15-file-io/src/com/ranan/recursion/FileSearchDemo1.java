package com.ranan.recursion;

import java.io.File;

public class FileSearchDemo1 {
    public static void main(String[] args) {
        // 目标：查找D盘下的PlantsVsZombies.exe的位置
        File dir = new File("D:/");
        searchFile(dir,"PlantsVsZombies.exe");
    }

    /**
     * 搜索文件
     * @param dir 搜索的目录
     * @param filename 搜索的文件名
     */
    public static void searchFile(File dir,String filename){
        // 1.判断极端情况
        if(dir == null || !dir.exists() || dir.isFile() || filename == null){
            return;
        }

        // 2.获取该目录下的所有一级文件/文件夹
        File[] files = dir.listFiles();

        // 3.判断当前目录下是否存在一级文件对象，存在才能遍历
        if(files != null && files.length > 0){
            // 4.遍历一级文件对象
            for(File file:files){
                if(file.isFile() && file.getName().contains(filename)){
                    System.out.println("找到:" + file.getAbsolutePath());
                    return;
                } else {
                    searchFile(file,filename);
                }
            }
        }
    }
}

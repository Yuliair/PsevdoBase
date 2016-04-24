package main;

import cons.Const;
import watcher.WatchDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static cons.Const.FileOutAbs;

/**
 * Created by Юлия on 24.04.2016.
 */
public class Main {
    public static void main(String[] args){

       // System.out.println("Working Directory = " +
       //         System.getProperty("user.dir"));



        try {
            WatchDir watcher = new WatchDir(Paths.get(Const.folderFileIn));
            Manager manager = new Manager(watcher);

            File myFolder = new File(Const.folderFileIn);
            watcher.processEvents();
            File[] files = myFolder.listFiles();

            for(File file: files ){
                System.out.println(Paths.get(file.getPath()));
                System.out.println();
               manager.handleFile(Paths.get(file.getPath()));
            }
            System.out.println(files.length);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with input folder");
        }


       // ExecutorService executor = Executors.newFixedThreadPool(10);
    }




}


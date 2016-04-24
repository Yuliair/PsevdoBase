package main;

import cons.Const;
import fileswork.FileHandle;
import watcher.DirChangeListener;
import map.MapMy;

import java.nio.file.Path;

import watcher.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Юлия on 24.04.2016.
 */
public class Manager implements DirChangeListener {
    MapMy map;
    WatchDir watcher;
    ExecutorService executor;


    public Manager(WatchDir watcher) throws IOException {

        this.map = new MapMy();
        watcher.addListener(this);
        executor = Executors.newFixedThreadPool(Const.threadsCount);
        this.watcher = watcher;
    }

    public void closeAll(){
        //toDo   executor.shutdown();, close files
    }



    @Override
    public void handleFile(Path file) {
        if(file != null){
            FileHandle fileHandler = new FileHandle(file, map);
            executor.submit(fileHandler);
        }

    }
}


//    public void handleFile(String file) {
//
//    }





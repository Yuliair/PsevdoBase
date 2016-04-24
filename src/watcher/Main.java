package watcher;

import java.io.IOException;
import java.nio.file.*;

class Main {

    public static Boolean stopped;


    public static void main(String[] args) throws IOException {
        stopped = false;



        if (args.length == 0 || args.length > 1)
            usage();
        // register directory and process its events
        Path dir = Paths.get(args[0]);
        Exiter exiter = new Exiter();
        exiter.run();
        new WatchDir(dir).processEvents();

    }

    static void usage() {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }
}

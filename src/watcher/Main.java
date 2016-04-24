package watcher;

import main.Exiter;
import map.Key;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;


class Main {




    public static void main(String[] args) throws IOException {
        HashSet<Key> keys = new HashSet<>();
        Key key1 = (new Key(11, "user1", "thhp"));
        Key key2 = (new Key(11, "user1", "thhp"));
        //System.out.println(keys.contains(new Key(11, "user1", "thhp")));
        Key key3 = (new Key(11, "user1", "thhp4"));
        System.out.println(key1.compareTo(key2));
        System.out.println(key3.compareTo(key2));


//            System.out.println(keys.size());
//            Iterator<Key> it = keys.iterator();
//
//        while (it.hasNext()){
//            Key key = it.next();
//            System.out.println(key.getDay()+key.getUrl()+ key.getUser());
//        }
//        System.out.println(keys.size());

//
//         if (args.length == 0 || args.length > 1)
//            usage();
//        // register directory and process its events
//        Path dir = Paths.get(args[0]);
//        Exiter exiter = new Exiter();
//      //  exiter.run();
//        new WatchDir(dir).processEvents();


    }

    static void usage() {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }
}

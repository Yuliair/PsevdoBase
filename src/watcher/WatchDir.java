package watcher;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Example to watch a directory (or tree) for changes to files.
 */
public class WatchDir implements Runnable{

    private final WatchService watcher;
    WatchKey watchKey;
    Path path;



    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        this.watchKey = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        this.path = dir;
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    public WatchDir(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
            register(dir);
    }

    /**
     * Process all events for keys queued to the watcher
     */
    public void run() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
              processEvent(event);
            }

            boolean valid = key.reset();
            if (!valid) {
                // all directories are inaccessible
                //if you will delete all files before delete folder everything will be ok in windows
                break;
            }

        }
    }

    private void processEvent(  WatchEvent event){ //future task
        Path dir = this.path;
            WatchEvent.Kind kind = event.kind();

            if (kind != OVERFLOW && kind != ENTRY_DELETE) {
                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);
                ////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //в таски должна положиться обработка файла

               // System.out.format("%s: %s\n", event.kind().name(),"-"+ child);
                // child - path from src to file
                for (DirChangeListener listener : listeners)
                   listener.handleFile(child);
            }
        }

    private List<DirChangeListener> listeners = new ArrayList<DirChangeListener>();

    public void addListener(DirChangeListener toAdd) {
        listeners.add(toAdd);
    }


    }



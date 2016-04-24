package fileswork;

/**
 * Created by Юлия on 23.04.2016.
 */
import map.MapMy;
import cons.Const;

import java.io.*;
import java.nio.file.Path;


public class FileHandle implements Runnable{
    Path file;
    MapMy mapMy;


    public FileHandle(Path file, MapMy mapMy) {
        this.file = file;
        this.mapMy = mapMy;
    }

    public void run(){
        BufferedReader br = null;
        try {
            String line;

            br = new BufferedReader(new FileReader(String.valueOf(file)));
            while ((line = br.readLine()) != null) {
                mapMy.put(line);
            }
         // mapMy.print(Const.csvFileOut);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
    }




}
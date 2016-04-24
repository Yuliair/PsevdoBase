package map;

import cons.Const;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Юлия on 24.04.2016.
 */
public class MapMy {
    TreeMap<Key, HashSet<Session>> mapKey;
   // HashMap<String, LinkedList<Session>> mapFile;

    public MapMy(){
        mapKey = new TreeMap<Key, HashSet<Session>>();
      //  mapFile = new HashMap<String, LinkedList<Session>>();
    }


    public void print(String fileOut) {
        if (fileOut != null) {
            BufferedWriter bw = null;

           long dayold = -1;
            Key keyCur;
            try {
                bw = new BufferedWriter(new FileWriter(fileOut));
                ArrayList<Key> keys =new ArrayList( mapKey.keySet());
                Collections.sort(keys);
                Iterator<Key> iterKey = keys.iterator();
              while (iterKey.hasNext()){//toDO return
                  keyCur = iterKey.next();
                  HashSet<Session> sesionsCur = mapKey.get(keyCur);
                    if (keyCur.getDay()!=(dayold)) {
                        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                        bw.write(format.format(new Date((keyCur.getDay()) * Const.secInDay * Const.millSinS)) + "\n");
                        dayold = keyCur.getDay();
                    }
                    bw.write(keyCur.getUser() + ',' + keyCur.getUrl() + ',' + this.avr(sesionsCur) + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

        }
    }

    public void put(String line){

             if (line != null) {
                 Boolean smthNew = false;
            String[] entity = line.split(Const.cvsSplitBy);
            //0 - date, 1 - user, 2 - url, 3 - duration
            Long timeStart = Long.parseLong(entity[0]);
            Long duration = Long.parseLong(entity[3]);

            String file = "";


            if(Go.dayOf(timeStart) != Go.dayOf(timeStart + duration)){

                Session session = new Session(timeStart, entity[1], entity[2], file, (Go.dayOf(timeStart)+1)*Const.secInDay -timeStart);
                if (this.put(session)) smthNew = true;
                session = new Session(Go.dayOf(timeStart + duration)*Const.secInDay, entity[1], entity[2], file, (timeStart+duration) - Go.dayOf(timeStart + duration)*Const.secInDay);
                if (this.put(session)) smthNew = true;
                for (long i = Go.dayOf(timeStart)+1; i < Go.dayOf(timeStart + duration); i++ ){
                    session = new Session(i*Const.secInDay,  entity[1], entity[2], file, Const.secInDay );
                    if (this.put(session)) smthNew = true;
                }
            }
            else{
                Session session = new Session(timeStart, entity[1], entity[2], file, duration);
                if (this.put(session)) smthNew = true;
            }
            if (smthNew){
                print(Const.folderFileOut+"avg_file.csv");
            }




        }
    }

    private Boolean put(Session session) {
        HashSet<Session> sessions = mapKey.get(session.getKey());
        if (sessions == null) {
            sessions = new HashSet();
            sessions.add(session);
            mapKey.put(session.getKey(), sessions);
            return true;
        } else {
            if(!sessions.contains(session)) {
                sessions.add(session);
                return true;
            }
        }
        return false;
    }

    private static long  avr(HashSet<Session> sessions){
        if(!sessions.isEmpty()) {
            Iterator<Session>  iterator = sessions.iterator();
            long sum = 0;
            while(iterator.hasNext()){
                sum = sum + iterator.next().getDuration();
            }
            return  sum / sessions.size();
        }
        else return 0;
    }


  /*  public void put1(Session session) {
        LinkedList<Session> sessions = mapKey.get(session.getKey());
        if (sessions == null) {
            sessions = new LinkedList<>();
            sessions.add(session);
            mapKey.put(session.getKey(), sessions);
        } else {
            sessions.add(session);
        }

        sessions = mapFile.get(session.getFileIn());
        if (sessions == null) {
            sessions = new LinkedList<>();
            sessions.add(session);
            mapFile.put(session.getFileIn(), sessions);
        } else {
            sessions.add(session);
        }
        //toDO recount output
    }
    */




}

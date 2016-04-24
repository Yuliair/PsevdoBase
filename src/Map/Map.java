package Map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Юлия on 24.04.2016.
 */
public class Map {
    HashMap<Key, LinkedList<Session>> mapKey;
    HashMap<String, LinkedList<Session>> mapFile;

    public Map(){
        mapKey = new HashMap<Key, LinkedList<Session>>();
        mapFile = new HashMap<String, LinkedList<Session>>();
    }


    public void put(Session session) {
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

    public void delete(String fileIn){
       LinkedList<Session> sessions = mapFile.get(fileIn);
       //LinkedList<LinkedList<Session>> toReCount = new LinkedList<>();
        HashSet<Key> toReCount = new HashSet<>();
        if (sessions != null){
            for( Session session: sessions){
                Key key = sessions.poll().getKey();
                toReCount.add(key);
                mapKey.get(key).remove(session);








            }
            mapFile.remove(fileIn);
            //toDO recount output

        }
    }


    public void checkModif(String fileIn){

    }
}

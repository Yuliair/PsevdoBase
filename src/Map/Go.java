package Map;

import constants.Const;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Created by Юлия on 23.04.2016.
 */
public class Go {


    public  static long dayOf(long time){
        return time/Const.secInDay;
    }

    public void run(){

    }

    public void newEntity(String line){

        Map map = new Map();



        if (line != null) {

            String[] entity = line.split(Const.cvsSplitBy);
            //0 - date, 1 - user, 2 - url, 3 - duration
            Long timeStart = Long.parseLong(entity[0]);
            Long duration = Long.parseLong(entity[3]);


               if(dayOf(timeStart) != dayOf(timeStart+duration)){
                   String file = "";
                   Session session = new Session(timeStart, entity[1], entity[2], file, (dayOf(timeStart)+1)*Const.secInDay -timeStart);
                   map.put(session);
                   session = new Session(dayOf(timeStart+duration)*Const.secInDay, entity[1], entity[2], file, (timeStart+duration) - dayOf(timeStart+duration)*Const.secInDay);
                   map.put(session);
                   for (long i = dayOf(timeStart)+1; i < dayOf(timeStart+duration); i++ ){
                       session = new Session(i+Const.secInDay,  entity[1], entity[2], file, Const.secInDay );
                       map.put(session);
                   }
               }


        }
    }







    public void deleteFromMaps(){

    }
}

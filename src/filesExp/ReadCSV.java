package filesExp;

/**
 * Created by Юлия on 23.04.2016.
 */
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import Map.*;

public class ReadCSV {

    public static void main(String[] args) {

        ReadCSV obj = new ReadCSV();
        obj.run();

    }

    public void run() {



       // String csvFileIn = System.getProperty("user.dir")+"/src/filesExp/smth.csv" ;
        String csvFileIn = "src/smth.csv" ;
        String csvFileOut = "src/out.csv" ;
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFileIn));
            bw = new BufferedWriter(new FileWriter(csvFileOut));
            while ((line = br.readLine()) != null) {

                String[] entity = line.split(cvsSplitBy);
                Date date = new Date(Long.parseLong(entity[0])*1000);
                //date.getDay()+"-"+date.getMonth()+"-"+date.getYear(
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy",  Locale.ENGLISH);


                bw.write("User " + entity[1]
                        + "  spent " + entity[3] + " on the "+  entity[2]+" since "+format.format(date) +"\n");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println("Done");
    }

}
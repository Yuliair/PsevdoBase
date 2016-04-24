package main;

import java.util.Scanner;

/**
 * Created by Юлия on 22.04.2016.
 */
public class Exiter extends Thread {

    public Exiter(){

    }

    public void run(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            if (string.equals("stop")){ ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    //toDo close files
                    System.exit(0);
                }
            }
        }
    }


}

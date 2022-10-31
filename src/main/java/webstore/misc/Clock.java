package webstore.misc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock extends Thread {

    private String timeLine;

    public Clock() {
       this.timeLine = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy"));
    }

    public String getTimeLine() {
         return this.timeLine;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Thread sleeps & updates ever 1 second, so the clock changes every 1 second.
                timeLine = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy"));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}

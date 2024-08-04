package thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class thread implements Runnable{

    public thread(int num, int startCnt, int endCnt){
        this.num = num;
        this.startCnt = startCnt;
        this.endCnt = endCnt;
    }

    public int num;
    public int startCnt;
    public int endCnt;

    @Override
    public void run() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = simpleDateFormat.format(cal.getTime());

        for(int i=startCnt; i<=endCnt; i++){
            System.out.println("thread-" + num + " : " + i + " / " + time);
        }


    }


}

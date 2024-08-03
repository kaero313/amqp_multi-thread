package amqp;

import amqp .amqpProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class amqpPublisher {

    public int count = 0;

    @RequestMapping(value = "test", method = {RequestMethod.GET, RequestMethod.POST})
    public String test(@RequestBody String request){

        System.out.println("test");

        return request;
    }

    @RequestMapping(value = "amqp", method = {RequestMethod.GET, RequestMethod.POST})
    public void amqp(@RequestBody String request, HttpServletResponse response){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = simpleDateFormat.format(cal.getTime());

        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(request);
            writer.close();
        }catch (IOException e1){

        }

        amqpProducer amqpProducer = new amqpProducer();
        amqpProducer.sendMessage("receiver", String.valueOf(count++) + " / " + time);

    }


    @RequestMapping(value = "none_amqp", method = {RequestMethod.GET, RequestMethod.POST})
    public void none_amqp(@RequestBody String request, HttpServletResponse response){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = simpleDateFormat.format(cal.getTime());

        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(request);
            writer.close();
        }catch (IOException e1){

        }

        System.out.println(count++ + " / " + time);

    }

    public static void main(String args[]){

        System.out.println("main");

    }

}



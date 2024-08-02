package amqp;

import amqp.amqpProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class test {

    @RequestMapping(value = "ccc", method = {RequestMethod.GET, RequestMethod.POST})
    public String cc(@RequestBody String request){

        System.out.println(request);

        return request;
    }

    @RequestMapping(value = "amqp", method = {RequestMethod.GET, RequestMethod.POST})
    public String mq(@RequestBody String request){

        amqpProducer amqpProducer = new amqpProducer();
        amqpProducer.sendMessage("receiver", "testmsg@@@");

        return request;
    }

    public static void main(String args[]){

        System.out.println("1213213213123 @@@@@@@23");

    }

}



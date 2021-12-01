import javax.jms.JMSException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Sensor weather = new Sensor();

                ExtractData parse = new ExtractData(weather.GetWeather());

                MessageWriter message = new MessageWriter();
                Broker jsonMessage = new Broker();

                try {
                    jsonMessage.Send(message.Write(parse.extract()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 15*60*1000);
    }
}
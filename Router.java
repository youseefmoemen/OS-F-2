import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Router{
    int size;
    Vector<Device> devices;
    Semaphore connected;
    Semaphore valid;
    Router(int init){
        size = init;
        devices = new Vector<Device>(size);
        valid = new Semaphore(size);
        connected = new Semaphore(0);
    }
    public void connect(Device d) {
        valid.P(d);
        devices.add(d);
        connected.V();
    }
    public void disconnect(Device d) {
        connected.P(d);
        devices.remove(d);
        valid.V();
    }
    public void action(int t){
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

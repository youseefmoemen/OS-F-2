import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Router{
    int size;
    Vector<Device> devices;
    Semaphore connected;
    Semaphore valid;
    FileWriter out;
    Router(int init){
        size = init;
        devices = new Vector<Device>(size);
        valid = new Semaphore(size);
        connected = new Semaphore(0);
        try {
            out = new FileWriter("out.text", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void connect(Device d) {
        valid.P(d);
        devices.add(d);
        
        try {
            System.out.println("GG");
            out.write("Connection " + devices.indexOf(d) + ": " + d.name + " Occupied\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        connected.V();
    }
    public void disconnect(Device d) {
        connected.P(d);
        try {
            out.write("Connection "+ devices.indexOf(d) + ": " + d.name + " logged out\n");
        } catch (IOException e) {
            e.printStackTrace();
        }        
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

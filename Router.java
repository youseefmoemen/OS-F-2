import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Router{
    int size;
    ArrayList<Device> devices;
    Semaphore connected;
    Semaphore valid;
    FileWriter out;
    Router(int init){
        size = init;
        devices = new ArrayList<Device>(size);
        valid = new Semaphore(size);
        connected = new Semaphore(0);
    }
    public void connect(Device d) {
        valid.P(d, true);
        devices.add(d);
        try {
            FileWriter out = new FileWriter("out.text", true);
            out.write("Connection " + devices.indexOf(d) + ": " + d.name + " Occupied\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connected.V();
    }
    public void disconnect(Device d) {
        connected.P(d, false);
        try {
            FileWriter out = new FileWriter("out.text", true);
            out.write("Connection "+ devices.indexOf(d) + ": " + d.name + " logged out\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }       
        devices.remove(d);
        valid.V();
    }
    public void action(int t, Device d){
        try {
            try {
                FileWriter out = new FileWriter("out.text", true);
                out.write("Connection " + devices.indexOf(d) + ": " + d.name + " perform online action takes " + t + " seconds\n");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

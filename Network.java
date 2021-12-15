import java.util.Scanner;
import java.util.Vector;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Random;

class Semaphore {
    private int value;

    Semaphore(int init) {
        value = init;
    }

    public synchronized void P(Device d, boolean connect) { // Wait
        value--;
        if (value < 0) {
            if (connect && !d.name.equals("-1")) {
                try {
                    FileWriter out = new FileWriter("out.text", true);
                    out.write(d.name + " " + d.type + " Arrived and waiting\n");
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                if (connect) {
                    FileWriter out = new FileWriter("out.text", true);
                    out.write(d.name + " " + d.type + " Arrived\n");
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void V() { // Signal
        value++;
        if (value <= 0)
            notify();
    }
}

class Router {
    int size;
    Device devices[];
    Semaphore connected;
    Semaphore valid;

    Router(int init) {
        size = init;
        devices = new Device[size];
        for (int i = 0; i < size; i++) {
            devices[i] = new Device("-1", "-1", this);
        }
        valid = new Semaphore(size);
        connected = new Semaphore(0);
    }

    public void connect(Device d) {
        valid.P(d, true);
        int hold = -1;
        for (int i = 0; i < size; i++) {
            if (devices[i].name.equals("-1")) {
                devices[i] = d;
                hold = i;
                break;
            }
        }
        try {
            FileWriter out = new FileWriter("out.text", true);
            out.write("Connection " + hold + ": " + d.name + " Occupied\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connected.V();
    }

    public void login(Device d) {
        int hold = -1;
        for (int i = 0; i < size; i++) {
            if (devices[i].equals(d)) {
                hold = i;
                break;
            }
        }
        try {
            FileWriter out = new FileWriter("out.text", true);
            out.write("Connection " + hold + ": " + d.name + " login\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(Device d) {
        connected.P(d, false);
        int hold = -1;
        for (int i = 0; i < size; i++) {
            if (devices[i].equals(d)) {
                devices[i] = new Device("-1", "-1", this);
                hold = i;
                break;
            }
        }
        try {
            FileWriter out = new FileWriter("out.text", true);
            out.write("Connection " + hold + ": " + d.name + " logged out\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        valid.V();
    }

    public void action(int t, Device d) {
        try {
            try {
                FileWriter out = new FileWriter("out.text", true);
                int hold = -1;
                for (int i = 0; i < size; i++) {
                    if (devices[i].equals(d)) {
                        hold = i;
                        break;
                    }
                }
                out.write("Connection " + hold + ": " + d.name + " perform online action takes " + t + " seconds\n");
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

class Device extends Thread {
    public String name;
    public String type;
    Router connectRouter;

    Device(String a, String b, Router r) {
        name = a;
        type = b;
        connectRouter = r;
    }

    public void run() {
        Random r = new Random();
        connectRouter.connect(this);
        connectRouter.login(this);
        connectRouter.action(r.nextInt(30), this);
        connectRouter.disconnect(this);
    }
}

public class Network {
    public static void main(String[] args) {

        int a, b;
        System.out.println("What is the number of WI-FI Connections");
        Scanner s = new Scanner(System.in);
        a = s.nextInt();
        System.out.println("What is the number of Client Devices");
        b = s.nextInt();
        Router router = new Router(a);
        String q, w;
        Vector<Device> vDevices = new Vector<>();
        for (int i = 0; i < b; i++) {
            System.out.println("Please enter the name and type of device " + (i + 1) + "seperated by space");
            q = s.next();
            w = s.next();
            Device d = new Device(q, w, router);
            vDevices.add(d);
        }
        for(int i = 0; i < b; i++){
            vDevices.get(i).start();
        }
        s.close();
    }
}

import java.io.FileWriter;
import java.io.IOException;

public class Semaphore {
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
                    System.out.println("Waiting " + d.name);
                    wait();
                    System.out.println("Notified " + d.name);
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
        if (value >= 0)
            notify();
    }
}

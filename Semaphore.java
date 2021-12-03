import java.io.FileWriter;
import java.io.IOException;

public class Semaphore {
    private int value;

    Semaphore(int init) {
        value = init;
    }

    public synchronized void P(Device d, boolean connect) { // Wait
        if (connect) {
            value--;
            if (value < 0) {
                try {
                    try {
                        FileWriter out = new FileWriter("out.text", true);
                        out.write(d.name + " " + d.type + " Arrived and waiting\n");
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    wait();
                } catch (InterruptedException e) {
                }
            } else {
                try {
                    FileWriter out = new FileWriter("out.text", true);
                    out.write(d.name + " " + d.type + " Arrived\n");
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void V() { // Signal
        value++;
        if (value >= 0)
            notify();
    }
}

import java.util.Scanner;
import java.util.Vector;


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

import java.util.Scanner;

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
        System.out.println("SS: "+router.size);
        for (int i = 0; i < b; i++) {
            q = s.nextLine();
            w = s.nextLine();
            Device d = new Device(q, w, router);
            d.start();
        }
        s.close();
    }
}

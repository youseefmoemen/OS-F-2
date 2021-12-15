// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.concurrent.TimeUnit;


// public class Router{
//     int size;
//     Device devices[];
//     Semaphore connected;
//     Semaphore valid;
//     Router(int init){
//         size = init;
//         devices = new Device[size];
//         for (int i = 0; i < size; i++){
//             devices[i] = new Device("-1", "-1", this);
//         }
//         valid = new Semaphore(size);
//         connected = new Semaphore(0);
//     }
//     public void connect(Device d) {
//         valid.P(d, true);
//         int hold = -1;
//         for(int i = 0; i < size; i++){
//             if(devices[i].name.equals("-1")){
//                 devices[i] =  d;
//                 hold = i;
//                 break;
//             }
//         }
//         try {
//             FileWriter out = new FileWriter("out.text", true);
//             out.write("Connection " + hold + ": " + d.name + " Occupied\n");
//             out.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         connected.V();
//     }
//     public void login(Device d){
//         int hold = -1;
//         for(int i = 0; i < size; i++){
//             if(devices[i].equals(d)){
//                 hold = i;
//                 break;
//             }
//         }
//         try {
//             FileWriter out = new FileWriter("out.text", true);
//             out.write("Connection " + hold + ": " + d.name + " login\n");
//             out.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//     public void disconnect(Device d) {
//         connected.P(d, false);
//         int hold = -1;
//         for(int i = 0; i < size; i++){
//             if(devices[i].equals(d)){
//                 devices[i] = new Device("-1", "-1", this);
//                 hold = i;
//                 break;
//             }
//         }
//         try {
//             FileWriter out = new FileWriter("out.text", true);
//             out.write("Connection "+ hold + ": " + d.name + " logged out\n");
//             out.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         valid.V();
//     }
//     public void action(int t, Device d){
//         try {
//             try {
//                 FileWriter out = new FileWriter("out.text", true);
//                 int hold = -1;
//                 for(int i = 0; i < size; i++){
//                     if(devices[i].equals(d)){
//                         hold = i;
//                         break;
//                     }
//                 }
//                 out.write("Connection " + hold + ": " + d.name + " perform online action takes " + t + " seconds\n");
//                 out.close();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }            
//             TimeUnit.SECONDS.sleep(t);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }
// }

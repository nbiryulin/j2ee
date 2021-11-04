import java.util.Arrays;

public class PrintModelNamesThread extends Thread {

    private final Transport transport;

    public PrintModelNamesThread(String name, Transport transport) {
        super(name);
        this.transport = transport;
    }

    public void run() {
        System.out.println(getName() + " started...");
        Arrays.stream(transport.getModelNames()).forEach(v -> {
            System.out.println(v);

//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
        System.out.println(getName() + " finished..");
    }
}

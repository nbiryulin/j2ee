import java.util.Arrays;

public class PrintModelNamesThread extends Thread {

    private final Transport transport;

    public PrintModelNamesThread(String name, Transport transport) {
        super(name);
        this.transport = transport;
    }

    public void run() {
        System.out.println(getName() + " started...");
        Arrays.stream(transport.getModelNames()).forEach(System.out::println);
        System.out.println(getName() + " finished..");
    }
}

import java.util.Arrays;

public class PrintPricesThread extends Thread {

    private final Transport transport;

    public PrintPricesThread(String name, Transport transport) {
        super(name);
        this.transport = transport;
    }

    public void run() {
        System.out.println(getName() + " started...");
        Arrays.stream(transport.getPrices()).forEach(System.out::println);
        System.out.println(getName() + " finished...");
    }
}

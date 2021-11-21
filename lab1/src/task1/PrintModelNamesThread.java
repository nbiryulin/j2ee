package task1;

import java.util.Arrays;
import model.Transport;

public class PrintModelNamesThread extends Thread {

    private final Transport transport;

    public PrintModelNamesThread(String name, Transport transport) {
        super(name);
        this.transport = transport;
    }

    public void run() {
        Arrays.stream(transport.getModelNames()).forEach(System.out::println);
    }
}

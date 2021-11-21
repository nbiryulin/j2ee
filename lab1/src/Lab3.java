import exceptions.DuplicateModelNameException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import model.Moto;
import model.Transport;
import task1.PrintModelNamesThread;
import task1.PrintPricesThread;
import task2.PrintModelsRunnable;
import task2.PrintModelsRunnableV2;
import task2.PrintPricesRunnable;
import task2.PrintPricesRunnableV2;
import task2.TransportSynchronizer;
import task2.TransportSynchronizerV2;
import task3.ModelsRunnable;
import task3.PricesRunnable;
import task4.Task4MarksRunnable;
import task5.Task5MarksRunnable;
import utils.Utils;

public class Lab3 {


    public static void main(String[] args) throws DuplicateModelNameException, InterruptedException {
        Transport transport = Utils.generateCar();
        task1(transport);
        task2(transport);
        task3(transport);

        Transport[] transports = new Transport[4];
        transports[0] = new Moto("Mark 1", 1);
        transports[1] = new Moto("Mark 2", 1);
        transports[2] = new Moto("Mark 3", 1);
        transports[3] = new Moto("Mark 4", 1);
        task4(transports);

        task5();


    }

    private static void task1(Transport transport) {
        PrintModelNamesThread names = new PrintModelNamesThread("names", transport);
        PrintPricesThread prices = new PrintPricesThread("prices", transport);
        prices.setPriority(Thread.MIN_PRIORITY);
        names.setPriority(Thread.MAX_PRIORITY);

        names.start();
        prices.start();
        try {
            names.join();
            prices.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void task2(Transport transport) {
        TransportSynchronizer transportSynchronizer = new TransportSynchronizer(1, true);
        PrintPricesRunnable printPricesRunnable = new PrintPricesRunnable(transport,
            transportSynchronizer);
        PrintModelsRunnable printModelsRunnable = new PrintModelsRunnable(transport,
            transportSynchronizer);
        new Thread(printModelsRunnable).start();
        new Thread(printPricesRunnable).start();

        TransportSynchronizerV2 transportSynchronizerV2 = new TransportSynchronizerV2(transport);
        PrintModelsRunnableV2 printModelsRunnableV2 = new PrintModelsRunnableV2(
            transportSynchronizerV2, transport);
        PrintPricesRunnableV2 printPricesRunnableV2 = new PrintPricesRunnableV2(
            transportSynchronizerV2, transport);
        new Thread(printModelsRunnableV2).start();
        new Thread(printPricesRunnableV2).start();
    }

    private static void task3(Transport transport) {
        ReentrantLock lock = new ReentrantLock();
        ModelsRunnable modelsRunnable = new ModelsRunnable(lock, transport);
        PricesRunnable pricesRunnable = new PricesRunnable(lock, transport);
        new Thread(modelsRunnable).start();
        new Thread(pricesRunnable).start();

    }

    private static void task4(Transport[] transports) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < transports.length; i++) {
            service.submit(new Task4MarksRunnable(transports[i]));
        }
        service.shutdown();
    }

    private static void task5() throws InterruptedException {
        ArrayBlockingQueue<Transport> arrayBlockingQueue = new ArrayBlockingQueue<Transport>(5);
        String[] files =  {"/Users/nbiryulin/IdeaProjects/j2ee1/lab1/src/files/mark1" ,
            "/Users/nbiryulin/IdeaProjects/j2ee1/lab1/src/files/mark2",
            "/Users/nbiryulin/IdeaProjects/j2ee1/lab1/src/files/mark3",
            "/Users/nbiryulin/IdeaProjects/j2ee1/lab1/src/files/mark4",
            "/Users/nbiryulin/IdeaProjects/j2ee1/lab1/src/files/mark5"
        };
        Arrays.stream(files).forEach(v -> {
            try {
                new Thread(new Task5MarksRunnable(v, arrayBlockingQueue)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 5; i++) {
            Transport transport = arrayBlockingQueue.take();
            System.out.println(transport.getMark());
        }

    }
}


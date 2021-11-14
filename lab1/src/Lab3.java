import exceptions.DuplicateModelNameException;

public class Lab3 {


    public static void main(String[] args) throws DuplicateModelNameException {
        Transport transport = Utils.generateCar();
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
        TransportSynchronizer transportSynchronizer = new TransportSynchronizer(1, true);
        PrintPricesRunnable printPricesRunnable = new PrintPricesRunnable(transport, transportSynchronizer);
        PrintModelsRunnable printModelsRunnable = new PrintModelsRunnable(transport, transportSynchronizer);
        new Thread(printModelsRunnable).start();
        new Thread(printPricesRunnable).start();
    }
}

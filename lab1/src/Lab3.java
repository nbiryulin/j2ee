import exceptions.DuplicateModelNameException;

public class Lab3 {


    public static void main(String[] args) throws DuplicateModelNameException {
        Transport transport = Utils.generateCar();
        //withoutPriority(transport);
        //pricesPriority(transport);
        namesPriority(transport);
    }

    private static void withoutPriority(Transport transport) {
        PrintModelNamesThread names = new PrintModelNamesThread("names", transport);
        PrintPricesThread prices = new PrintPricesThread("prices", transport);
        names.start();
        prices.start();
        try {
            names.join();
            prices.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void pricesPriority(Transport transport) {
        PrintModelNamesThread names = new PrintModelNamesThread("names", transport);
        PrintPricesThread prices = new PrintPricesThread("prices", transport);
        names.start();
        prices.start();
}

    private static void namesPriority(Transport transport) {
        PrintModelNamesThread names = new PrintModelNamesThread("names", transport);
        PrintPricesThread prices = new PrintPricesThread("prices", transport);
        names.setPriority(2);
        prices.setPriority(3);
        names.start();
        prices.start();
        try {
            names.join();
            prices.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

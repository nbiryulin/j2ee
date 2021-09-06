public class Motocycle {

    private class Model {
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;
    }

    private Model head = new Model();

    {
        head.prev = head;
        head.next = head;
    }

    private int size = 0;
// далее код по заданию
}

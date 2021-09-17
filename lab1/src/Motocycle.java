import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;

import java.util.HashMap;
import java.util.Map;

public class Motocycle implements Transport {

    @Override
    public String[] getModelNames() {
        String[] array = new String[size];
        Model model = head;
        for (int i = 0; i < size; i++) {
            array[i] = model.name;
            model = model.next;
        }
        return array;
    }

    public double getPriceByName(String name) throws NoSuchModelNameException {
        Map<String, Double> map = modelsToMap();
        if (!map.containsKey(name)) {
            throw new NoSuchModelNameException();
        }
        return map.get(name);
    }

    @Override
    public void setPriceByName(String name, int price) throws NoSuchModelNameException {

    }

    @Override
    public int[] getPrices() {
        return new int[0];
    }

    @Override
    public void addModel(String name, int price) throws DuplicateModelNameException {

    }

    @Override
    public void deleteModel(String name, int price) {

    }

    @Override
    public int getModelsLength() {
        return 0;
    }

    @Override
    public String getMark() {
        return null;
    }

    @Override
    public void setMark(String mark) {

    }

    private Map<String, Double> modelsToMap() {
        Map<String, Double> map = new HashMap<>();
        Model model = head;
        for (int i = 0; i < size; i++) {
            map.put(model.name, model.price);
            model = model.next;
        }
        return map;
    }

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

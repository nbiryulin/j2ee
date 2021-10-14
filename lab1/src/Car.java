import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import exceptions.NoSuchModelNameException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Car implements Transport {

    private String mark;
    private Model[] models;

    public Car(String mark, int count) {
        this.mark = mark;
        this.models = new Model[count];
    }

    public Model[] getModels() {
        return models;
    }

    public String[] getModelNames() {
        return Arrays
                .stream(models)
                .parallel()
                .map(Model::getName)
                .toArray(String[]::new);
    }

    public double getPriceByName(String name) throws NoSuchModelNameException {
        Map<String, Double> map = modelsToMap();
        if (!map.containsKey(name)) {
            throw new NoSuchModelNameException();
        }
        return map.get(name);
    }

    public void setPriceByName(String name, double price) throws NoSuchModelNameException {
        Map<String, Double> map = modelsToMap();
        if (!map.containsKey(name)) {
            throw new NoSuchModelNameException();
        } else if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        map.put(name, price);
        models = map.entrySet().stream().map(v -> new Model(v.getKey(), v.getValue())).toArray(Model[]::new);
    }

    public double[] getPrices() {
        return Arrays.stream(models).mapToDouble(Model::getPrice).toArray();
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        Map<String, Double> map = modelsToMap();
        if (map.containsKey(name)) {
            throw new DuplicateModelNameException();
        } else if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        int size = getModelsLength() + 1;
        Model[] array = Arrays.copyOf(models, size);
        array[getModelsLength()] = new Model(name, price);
        models = array;
    }

    private Map<String, Double> modelsToMap() {
        if (getModelsLength() == 0) {
            return new HashMap<>();
        }
        return Arrays.stream(models)
                .collect(
                        Collectors.toMap(
                                Model::getName,
                                Model::getPrice
                        )
                );
    }

    public void deleteModel(String name, double price) throws NoSuchModelNameException {
        int position = -1;
        Model model = new Model(name, price);
        for (int i = 0; i < getModelsLength(); i++) {
            if (models[i].equals(model)) {
                position = i;
            }
        }
        if (position == -1) {
            throw new NoSuchModelNameException();
        }
        //    Model[] copyArray = new Model[models.length - 1];
        // copyArray = Arrays.copyOf(models, position);
        //  System.arraycopy(models, 0, copyArray, 0, position);
        System.arraycopy(models, position + 1, models, position, models.length - position - 1);
        models = Arrays.copyOf(models, models.length - 1);
    }

    public int getModelsLength() {
        return (int) Arrays.stream(models).filter(Objects::nonNull).count();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException{
        Map<String, Double> map = modelsToMap();
        if (map.containsKey(newName)) {
            throw new DuplicateModelNameException();
        } else if (!map.containsKey(oldName)){
            throw new NoSuchModelNameException();
        }
        Double price = modelsToMap().get(oldName);
        map.remove(oldName);
        map.put(newName, price);
        models = map.entrySet().stream().map(v -> new Model(v.getKey(), v.getValue())).toArray(Model[]::new);
    }

    private class Model {
        private String name;

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Model model = (Model) o;
            return price == model.price && name.equals(model.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}

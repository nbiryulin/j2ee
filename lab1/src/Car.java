import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Car implements Transport {

    private String mark;
    private Model[] models;

    public Car(String mark, Model[] models) {
        this.mark = mark;
        this.models = models;
    }

    public Model[] getModels() {
        return models;
    }

    public void setModels(Model[] models) {
        this.models = models;
    }

    public String[] getModelNames() {
        return Arrays
                .stream(models)
                .parallel()
                .map(Model::getName)
                .toArray(String[]::new);
    }

    public int getPriceByName(String name) throws NoSuchModelNameException {
        Map<String, Integer> map = modelsToMap();
        if (!map.containsKey(name)) {
            throw new NoSuchModelNameException();
        }
        return map.get(name);
    }

    public void setPriceByName(String name, int price) throws NoSuchModelNameException {
        Map<String, Integer> map = modelsToMap();
        if (!map.containsKey(name)) {
            throw new NoSuchModelNameException();
        }
        map.put(name, price);
        models = map.entrySet().stream().map(v -> new Model(v.getKey(), v.getValue())).toArray(Model[]::new);
    }

    public int[] getPrices() {
        return Arrays.stream(models).mapToInt(Model::getPrice).toArray();
    }

    public void addModel(String name, int price) throws DuplicateModelNameException {
        Map<String, Integer> map = modelsToMap();
        if (map.containsKey(name)) {
            throw new DuplicateModelNameException();
        }
        int size = models.length + 1;
        Model[] array = Arrays.copyOf(models, size);
        array[models.length] = new Model(name, price);
        models = array;
    }

    private Map<String, Integer> modelsToMap() {
        return Arrays.stream(models)
                .collect(
                        Collectors.toMap(
                                Model::getName,
                                Model::getPrice
                        )
                );
    }

    public void deleteModel(String name, int price) {
        Model[] array = Arrays.copyOf(models, models.length - 1);
        int position = -1;
        Model model = new Model(name, price);
        for (int i = 0; i < models.length; i++) {
            if (models[i].equals(model)) {
                position = i;
            }
        }
        if (position != -1) {
            Model[] first = new Model[position];
            if (position != 0) {
                System.arraycopy(models, 0, first, position - 1, position);
            }
            Model[] second = new Model[models.length - position];
            if (position != models.length) {
                System.arraycopy(models, position + 1, second, models.length, models.length - position);
            }
            array = Stream.of(first, second).flatMap(Stream::of).toArray(Model[]::new);
            models = array;
        }
    }

    public int getModelsLength() {
        return models.length;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    static class Model {
        private String name;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        private int price;

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

        public Model(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }
}

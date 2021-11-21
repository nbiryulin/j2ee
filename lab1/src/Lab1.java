import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import exceptions.NoSuchModelNameException;

import java.util.Arrays;
import model.Car;
import model.Moto;
import model.Transport;
import utils.Utils;

public class Lab1 {

    public static void main(String[] args) {
        Car car = new Car("Toyota", 5);
        test(car);
        Moto moto = new Moto("harley", 5);
        test(moto);
    }

    static void test(Transport car) {
        try {
            car.addModel("1", 1.0);
            car.addModel("2", 2.0);
            car.addModel("3", 3.0);
            System.out.println(Utils.countAverage(car));
            Utils.printNames(car);
            Utils.printPrices(car);
            car.setPriceByName("1", 4.0);
            System.out.println(car.getPriceByName("1"));
            car.deleteModel("2", 2.0);
            System.out.println(Arrays.toString(car.getPrices()));
            car.setMark("Not toyota");
            System.out.println(car.getMark());
            car.setModelName("1", "4");
            Utils.printNames(car);
            car.deleteModel("3", 3.0);
            car.deleteModel("model.Moto 1", 1.0);
          //  car.deleteModel("foo", 1.0);
            car.setModelName( "foo", "4");

            Utils.printNames(car);
        } catch (DuplicateModelNameException e) {
            System.out.println("Name already exists");
        } catch (ModelPriceOutOfBoundsException e) {
            System.out.println("Price can't be NaN");
        } catch (NoSuchModelNameException e) {
            System.out.println("No such model name");
        }
    }



}

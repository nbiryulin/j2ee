import exceptions.DuplicateModelNameException;

import java.io.*;

public class Lab2 {


    public static void main(String[] args) throws DuplicateModelNameException, IOException, ClassNotFoundException {

        Car car = new Car();
       Transport transport = getTestTransport(car);
        test(transport);
    }

    private static Transport getTestTransport(Transport transport) throws DuplicateModelNameException {
        transport.setMark("mark");
        addModel(transport, "1", 1.0);
        addModel(transport, "2", 2.0);
        addModel(transport, "3", 3.0);
        return transport;
    }

    private static Transport addModel(Transport transport, String name, Double price) throws DuplicateModelNameException {
        transport.addModel(name, price);
        return transport;
    }

    private static void test(Transport transport) throws IOException, ClassNotFoundException {
        testSerialization(transport);
        testBytes(transport);
        testSymbols(transport);
        testSystem(transport);
    }

    private static void testSerialization(Transport transport) throws IOException, ClassNotFoundException {
        FileOutputStream outputStream = new FileOutputStream("/home/nbiryulin/IdeaProjects/j2ee1/lab1/out");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(transport);
        objectOutputStream.close();

        InputStream inputStream = new FileInputStream(new File("/home/nbiryulin/IdeaProjects/j2ee1/lab1/out"));
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Transport a = (Transport) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(a.getMark());
        System.out.println(a.getModelsMap());
        assert transport.equals(a);
    }

    private static void testBytes(Transport transport) {
        try (FileOutputStream outputStream = new FileOutputStream("/home/nbiryulin/IdeaProjects/j2ee1/lab1/bytes");
             FileInputStream fileInputStream = new FileInputStream(new File("/home/nbiryulin/IdeaProjects/j2ee1/lab1/bytes"))
        ) {
            Utils.outputTransport(transport, outputStream);
            Transport result = Utils.inputTransport(fileInputStream);
            System.out.println(result.getMark());
            assert transport.equals(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testSymbols(Transport transport) {
        try (FileWriter outputStream = new FileWriter("/home/nbiryulin/IdeaProjects/j2ee1/lab1/bytes");
             FileReader fileInputStream = new FileReader("/home/nbiryulin/IdeaProjects/j2ee1/lab1/bytes")
        ){
            Utils.writeTransport(transport, outputStream);
            Transport result = Utils.readTransport(fileInputStream);
            System.out.println(result.getMark());
            assert transport.equals(result);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void testSystem(Transport transport) {
        try (OutputStreamWriter outputStream = new OutputStreamWriter(System.out);
             InputStreamReader fileInputStream = new InputStreamReader(System.in)
        ){
            Utils.writeTransport(transport, outputStream);
            Transport result = Utils.readTransport(fileInputStream);
            System.out.println(result.getMark());
            assert transport.equals(result);
        } catch(IOException e){
            e.printStackTrace();
        }
    }


}

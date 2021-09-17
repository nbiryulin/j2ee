import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;

public interface Transport {

    public String[] getModelNames();

    public void setPriceByName(String name, int price) throws NoSuchModelNameException;

    public int[] getPrices();

    public void addModel(String name, int price) throws DuplicateModelNameException;

    public void deleteModel(String name, int price);

    public int getModelsLength();

    public String getMark();

    public void setMark(String mark);
}

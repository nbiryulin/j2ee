import static java.lang.Thread.sleep;

public class PrintPricesRunnable implements Runnable {

  private double[] models;
  private TransportSynchronizer transportSynchronizer;
  private int counter = 0;

  public PrintPricesRunnable(Transport transport, TransportSynchronizer synchronizer) {
    this.models = transport.getPrices();
    this.transportSynchronizer = synchronizer;
  }

  @Override
  public void run() {
    while (counter < models.length) {
//      try {
        if (!transportSynchronizer.marker.get()) {
         // transportSynchronizer.acquire();
          System.out.println(models[counter]);
          counter++;
     //     transportSynchronizer.release();
          transportSynchronizer.marker.set(true);
        }
//      } catch (InterruptedException e) {
//        System.out.println(e.getMessage());
//      }
    }
  }
}

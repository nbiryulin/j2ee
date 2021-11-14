import static java.lang.Thread.sleep;

public class PrintModelsRunnable implements Runnable {

  private final String[] models;
  private TransportSynchronizer transportSynchronizer;
  private int counter = 0;

  public PrintModelsRunnable(Transport transport, TransportSynchronizer synchronizer) {
    this.models = transport.getModelNames();
    this.transportSynchronizer = synchronizer;
  }


  @Override
  public void run() {
    while (counter < models.length) {
      try {
        if (transportSynchronizer.marker.get()) {
      //    transportSynchronizer.acquire();
          sleep(1);
          System.out.println(models[counter]);
          counter++;
        //  transportSynchronizer.release();
          sleep(1);
          transportSynchronizer.marker.set(false);
        }
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());

      }
    }
  }
}

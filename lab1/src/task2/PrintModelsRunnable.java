package task2;

import model.Transport;

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
//      try {
        if (transportSynchronizer.marker.get()) {
      //    transportSynchronizer.acquire();
          System.out.println("Task 2 : " + models[counter]);
          counter++;
        //  transportSynchronizer.release();
          transportSynchronizer.marker.set(false);
        }
//      } catch (InterruptedException e) {
//        System.out.println(e.getMessage());
//
//      }
    }
  }
}

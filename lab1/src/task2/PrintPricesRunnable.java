package task2;

import static java.lang.Thread.sleep;

import model.Transport;
import task2.TransportSynchronizer;

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
          System.out.println("Task 2 : " + models[counter]);
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

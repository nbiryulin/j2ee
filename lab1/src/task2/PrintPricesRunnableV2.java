package task2;

import model.Transport;

public class PrintPricesRunnableV2 implements Runnable {

  private final TransportSynchronizerV2 transportSynchronizerV2;
  private final Transport transport;

  public PrintPricesRunnableV2(TransportSynchronizerV2 transportSynchronizerV2,
      Transport transport) {
    this.transportSynchronizerV2 = transportSynchronizerV2;
    this.transport = transport;
  }


  @Override
  public void run() {
    for (int i = 0; i < transport.getModelsLength(); i++) {
      try {
        transportSynchronizerV2.printPrice();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

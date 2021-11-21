package task4;

import model.Transport;

public class Task4MarksRunnable implements Runnable {

  private final Transport transport;

  public Task4MarksRunnable(Transport transport) {
    this.transport = transport;
  }

  @Override
  public void run() {
    System.out.println("Task 4 : " + transport.getMark());
  }
}

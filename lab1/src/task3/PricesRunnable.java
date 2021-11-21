package task3;

import java.util.concurrent.locks.ReentrantLock;
import model.Transport;

public class PricesRunnable implements Runnable{

  private final ReentrantLock lock;
  private final double[] prices;

  public PricesRunnable(ReentrantLock lock, Transport transport) {
    this.lock = lock;
    this.prices = transport.getPrices();
  }

  @Override
  public void run() {
    lock.lock(); // устанавливаем блокировку
    try{
      for (int i = 0; i < prices.length; i++){
        System.out.println("Task 3 : "  + prices[i]);
      }
    } finally{
      lock.unlock(); // снимаем блокировку
    }
  }
}

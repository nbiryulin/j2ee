package task3;

import java.util.concurrent.locks.ReentrantLock;
import model.Transport;

public class ModelsRunnable implements Runnable {

private final ReentrantLock lock;
private final String[] models;

  public ModelsRunnable(ReentrantLock lock, Transport transport) {
    this.lock = lock;
    this.models = transport.getModelNames();
  }

  @Override
  public void run() {
    lock.lock(); // устанавливаем блокировку
    try{
      for (int i = 0; i < models.length; i++){
        System.out.println("Task 3: " + models[i]);
      }
    } finally{
      lock.unlock(); // снимаем блокировку
    }
  }
}

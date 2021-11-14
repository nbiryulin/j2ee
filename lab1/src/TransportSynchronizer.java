import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class TransportSynchronizer extends Semaphore {

  public AtomicBoolean marker = new AtomicBoolean(true);

  public TransportSynchronizer(int permits) {
    super(permits);
  }

  public TransportSynchronizer(int permits, boolean fair) {
    super(permits, fair);
  }
}

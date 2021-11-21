package task5;

import java.awt.font.TransformAttribute;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import model.Moto;
import model.Transport;

public class Task5MarksRunnable implements Runnable{

  private final String mark;
  private final BlockingQueue<Transport> queue;

  public Task5MarksRunnable(String path, BlockingQueue<Transport> queue) throws IOException {
    this.mark = Files.readString(Path.of(path), StandardCharsets.UTF_8);
    this.queue = queue;
  }

  @Override
  public void run() {
    Transport transport = new Moto(mark, 1);
    try {
      queue.add(transport);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}

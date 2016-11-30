import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Dmitriy on 29-Nov-16.
 */
public class TransceiverToClient implements Runnable {
    private OutputStream out;
    private LinkedBlockingQueue<String> messages;

    TransceiverToClient(LinkedBlockingQueue<String> messages, OutputStream stream) {
        out = stream;
        this.messages = messages;
    }

    public void run() {
        while (true) {
            try {
                String msg = messages.take();
                out.write(msg.getBytes());
            } catch (InterruptedException | IOException exc) {
                System.err.println("transceiver to client broken");
            }
        }

    }


}

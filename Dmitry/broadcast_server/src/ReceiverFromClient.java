
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Dmitriy on 29-Nov-16.
 */
public class ReceiverFromClient implements Runnable {
    String msg;
    private InputStream in;
    private LinkedBlockingQueue<Msg> messages;
    private int id;

    ReceiverFromClient(int id, LinkedBlockingQueue<Msg> messages, InputStream stream) {
        in = stream;
        this.messages = messages;
        this.id = id;
    }

    public void run() {
        while (true) {
            try {
                byte[] arr = new byte[64];
                in.read(arr);
                msg = new String(arr);
            } catch (IOException exp) {
            }

            Msg message = new Msg(msg, id);

            try {
                messages.put(message);
            } catch (InterruptedException exp) {
            }
        }

    }

}

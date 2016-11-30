import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Dmitriy on 29-Nov-16.
 */
public class ConnectionToClient {
    public int clientId;
    private LinkedBlockingQueue<String> messages;

    public ConnectionToClient(int id, LinkedBlockingQueue<Msg> serverMsgs, InputStream in, OutputStream out) {
        messages = new LinkedBlockingQueue<String>();
        clientId = id;

        ReceiverFromClient receiver = new ReceiverFromClient(id, serverMsgs, in);
        Thread thread = new Thread(receiver);
        thread.start();

        TransceiverToClient transceiver = new TransceiverToClient(messages, out);
        thread = new Thread(transceiver);
        thread.start();
    }

    public void add(Msg msg) {
        try {
            messages.put(msg.msg);
        } catch (InterruptedException exc) {
        }
    }


}

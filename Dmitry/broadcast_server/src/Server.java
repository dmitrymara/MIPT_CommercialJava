
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by Dmitriy on 29-Nov-16.
 */
public class Server {

    private LinkedBlockingQueue<Msg> messages;
    private ArrayList<ConnectionToClient> clientList;

    public Server(int nClient) {
        System.out.println("server starting... ");
        messages = new LinkedBlockingQueue<Msg>();
        clientList = new ArrayList<ConnectionToClient>(nClient);

        clientList.add(new ConnectionToClient(0, messages, System.in, System.out));
        clientList.add(new ConnectionToClient(1, messages, null, System.out));
        clientList.add(new ConnectionToClient(2, messages, null, System.out));

        System.out.println("server started ");
    }

    public void run() {
        while (true) {
            try {
                Msg message = messages.take();
                sendToAll(message);
            } catch (InterruptedException exception) {
                System.out.println("messages queue in server broken");
            }
        }
    }

    public void sendToAll(Msg msg) {
        for (ConnectionToClient client : clientList) {
            if (client.clientId == msg.clientId) {
                continue;
            }
            client.add(msg);
        }

    }
}

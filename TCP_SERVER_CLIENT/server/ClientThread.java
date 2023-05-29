
import java.io.IOException;
import java.net.SocketException;
import java.nio.charset.Charset;

public class ClientThread extends Thread {
    private Gui gui;
    private Client client;
    private int id;

    public ClientThread(Gui gui, Client client, int id) {
        this.gui = gui;
        this.client = client;
        this.id = id;
    }

    public void disConnectClient() {
        try {
            if (client.outToKlient != null) {
                client.outToKlient.close();
            }
            if (client.inFromKlient != null) {
                client.inFromKlient.close();
            }
            if (id != 4) {
                gui.getServer().clientsInfo[id] = "no connection";
                gui.getServer().clientSocket[id].close();
                gui.updateStatus();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String modifiedSentence;
        byte[] bytes = new byte[1024];
        int correct;
        if (id == 4) {
            gui.writeToConsol("Connection attempt ");
            disConnectClient();
            return;
        }
        try {
            while (true) {
                if (gui.getServer().running) {
                    if (client.inFromKlient != null && (correct = client.inFromKlient.read(bytes)) != 0) {
                        if (correct == -1) {
                            gui.writeToConsol("Client " + gui.getServer().clientsInfo[id] + " disconnected");
                            disConnectClient();
                            gui.getServer().clientSocket[id].close();
                            return;
                        }
                        modifiedSentence = new String(bytes, 0, correct);

                        gui.writeToConsol(
                                "#" + (id + 1) + " Client:" + " " + gui.getServer().clientsInfo[id] + " sends: "
                                        + modifiedSentence);

                        byte[] buf = modifiedSentence.getBytes(Charset.forName("UTF-8"));
                        client.outToKlient.write(buf);

                    }
                } else {
                    disConnectClient();
                    return;
                }
            }
        } catch (SocketException e) {
            gui.writeToConsol("Client " + gui.getServer().clientsInfo[id] + " disconnected: Connection Reset");
            disConnectClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

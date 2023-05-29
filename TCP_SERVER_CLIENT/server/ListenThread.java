
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ListenThread extends Thread {
    private Gui gui;

    public ListenThread(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = gui.getServer().serverSocket.accept();
                int clientId = 4;

                for (int i = 0; i < 3; i++) {
                    if (gui.getServer().clientsInfo[i].equals("no connection")
                            || gui.getServer().clientSocket[i].isInputShutdown()) {
                        clientId = i;

                        String ip = clientSocket.getInetAddress().getHostAddress();
                        gui.getServer().clientsInfo[i] = ip + ':' + clientSocket.getPort();

                        gui.updateStatus();
                        gui.getServer().clientSocket[clientId] = clientSocket;

                        break;
                    }
                }

                gui.writeToConsol("Accepted connection from " + clientSocket.getInetAddress() + ':'
                        + clientSocket.getPort());

                Client client = new Client();
                client.outToKlient = new DataOutputStream(clientSocket.getOutputStream());
                client.inFromKlient = new DataInputStream(clientSocket.getInputStream());

                new ClientThread(gui, client, clientId).start();
            } catch (Exception e) {
                gui.writeToConsol("Server down");
                gui.getStatus()
                        .setText("<html>#1 no connection <br/> #2 no connection <br/> #3 no connection <br/></html>");
                return;
            }
        }
    }

}


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

public class ConnectionButtonHandler implements ActionListener {

    private Client klient;
    private Gui gui;

    public ConnectionButtonHandler(Client klient, Gui gui) {
        this.gui = gui;
        this.klient = klient;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (!gui.getStatus().getText().equals("connected")) {
                klient.connectToServer(gui);
                gui.writeToConsol("Connected to server");
                gui.getConButton().setText("Disconnect");
                gui.getStatus().setText("connected");
            } else {
                klient.closeConnection(gui);
                gui.writeToConsol("Disconneted from server");
                gui.getConButton().setText("Connect");
                gui.getStatus().setText("no connection");
            }

        } catch (UnknownHostException | ConnectException e1) {
            gui.writeToConsol("No connection: server not responding");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NumberFormatException e2) {
            gui.writeToConsol("Wrong port");
        }
    }
}

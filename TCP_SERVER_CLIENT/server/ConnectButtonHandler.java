
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ConnectButtonHandler implements ActionListener {

    private Gui gui;

    public ConnectButtonHandler(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        try {
            if (gui.getConButton().getText().equals("Start")) {
                gui.getServer().serverSocket = new ServerSocket(Integer.parseInt(gui.getPort().getText()), 0,
                        InetAddress.getByName("0.0.0.0"));
                gui.writeToConsol("Started server on port " + gui.getPort().getText());
                gui.getServer().running = true;
                gui.getServer().clientsInfo = new String[3];
                gui.getServer().clientSocket = new Socket[3];

                for (int i = 0; i < 3; i++) {
                    gui.getServer().clientsInfo[i] = "no connection";
                }

                gui.getConButton().setText("Stop");
                Thread listenThread = new ListenThread(gui);
                listenThread.start();
            } else {
                for (int i = 0; i < 3; i++) {
                    if (gui.getServer().clientSocket[i] != null) {
                        gui.getServer().clientSocket[i].close();
                    }
                }
                if (gui.getServer().serverSocket != null) {
                    gui.getServer().serverSocket.close();
                }
                gui.getServer().running = false;
                gui.getConButton().setText("Start");
                gui.getStatus()
                        .setText("<html>#1 no connection <br/> #2 no connection <br/> #3 no connection <br/></html>");
            }

        } catch (IOException e2) {
            gui.writeToConsol("Server can't start ");
        }
    }

}

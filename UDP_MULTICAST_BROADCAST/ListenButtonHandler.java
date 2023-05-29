
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ListenButtonHandler implements ActionListener {

    private Gui gui;

    public ListenButtonHandler(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (gui.getListenButton().getText().equals("Start listening")) {
                UdpConnection.multicastSocketReceive = new MulticastSocket(Integer.parseInt(gui.getPort().getText()));
                UdpConnection.multicastSocketReceive.setReuseAddress(true);
                UdpConnection.multicastSocketReceive.setSoTimeout(1000);
                UdpConnection.multicastSocketReceive.joinGroup(InetAddress.getByName(gui.getIp().getText()));
                UdpConnection.multicastSocketSend = new MulticastSocket();
                UdpConnection.multicastSocketSend.setReuseAddress(true);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        UdpConnectionHandler.receive(gui);

                    }
                };
                gui.writeToConsol("Start listening on " + gui.getIp().getText());
                gui.getListenButton().setText("Stop");
                UdpConnection.isOn = true;
                thread.start();
            } else {
                UdpConnection.isOn = false;
                UdpConnection.multicastSocketReceive.leaveGroup(InetAddress.getByName(gui.getIp().getText()));
                UdpConnection.multicastSocketReceive.close();
                UdpConnection.multicastSocketSend.close();
                gui.writeToConsol("Socket closed");
                gui.getListenButton().setText("Start listening");
            }
        } catch (NumberFormatException e2) {
            gui.writeToConsol("Wrong port");
        } catch (IOException e1) {
            gui.writeToConsol("Can't create sockets");
        }
    }
}

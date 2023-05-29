import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class UdpConnectionHandler {

    static public boolean send(Gui gui) throws NumberFormatException, IOException {
        if (gui.getMessage().getText().equals("")) {
            gui.writeToConsol("Wrong message: empty");
            return false;
        }
        byte[] buf = gui.getMessage().getText().getBytes(Charset.forName("UTF-8"));
        if (gui.getBroadcast().isSelected()) {
            DatagramPacket data = new DatagramPacket(buf, buf.length, InetAddress.getByName("255.255.255.255"),
                    Integer.parseInt(gui.getPort().getText()));
            UdpConnection.datagramSocket.send(data);
        } else {
            DatagramPacket data = new DatagramPacket(buf, buf.length, InetAddress.getByName(gui.getIp().getText()),
                    Integer.parseInt(gui.getPort().getText()));
            int ttl = 1;
            UdpConnection.multicastSocketSend.send(data, (byte) ttl);
        }
        return true;
    }

    static synchronized public boolean receive(Gui gui) {
        byte[] buf = new byte[1024];
        DatagramPacket data = new DatagramPacket(buf, buf.length);
        while (UdpConnection.isOn) {
            try {
                UdpConnection.multicastSocketReceive.receive(data);
                gui.writeToConsol("Received from " + data.getAddress() + ": " + new String(data.getData()));
            } catch (Exception e) {
                // Ignore exception
            }

        }
        return true;
    }
}

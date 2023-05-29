
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

public class Client {
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private DataInputStream inFromServer;

    public void connectToServer(Gui gui) throws NumberFormatException, UnknownHostException, IOException {
        clientSocket = new Socket(gui.getIp().getText(), Integer.parseInt(gui.getPort().getText()));
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new DataInputStream(clientSocket.getInputStream());
    }

    public void closeConnection(Gui gui) throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
        if (outToServer != null) {
            outToServer.close();
        }
        if (inFromServer != null) {
            inFromServer.close();
        }
    }

    public boolean sendMessage(Gui gui, SimpleAttributeSet attributeSet) throws BadLocationException, IOException {
        if (gui.getMessage().getText().equals("")) {
            gui.writeToConsol("Wrong message: empty");
            return false;
        }
        String modifiedSentence;
        gui.writeToConsol("To Server(" + gui.getMessage().getText().length() + "b): " + gui.getMessage().getText());
        byte[] buf = gui.getMessage().getText().getBytes(Charset.forName("UTF-8"));
        outToServer.write(buf);
        byte[] bytes = new byte[1024];
        int correct = inFromServer.read(bytes);
        modifiedSentence = new String(bytes, 0, correct);
        gui.writeToConsol("From Server(" + correct + "b): " + modifiedSentence);
        return true;
    }
}

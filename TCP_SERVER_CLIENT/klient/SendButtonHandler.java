
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

public class SendButtonHandler implements ActionListener {

    private Client klient;
    private Gui gui;
    private SimpleAttributeSet attributeSet;
    private boolean serverBusy;

    public SendButtonHandler(Client klient, Gui gui) {
        this.klient = klient;
        this.gui = gui;
        this.serverBusy = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (klient.sendMessage(gui, attributeSet)) {
                serverBusy = false;
            }
        } catch (IOException e1) {
            if (serverBusy) {
                gui.writeToConsol("SERVER BUSY ");
            } else {
                gui.writeToConsol("Lost connection to Server ");
            }
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }
}
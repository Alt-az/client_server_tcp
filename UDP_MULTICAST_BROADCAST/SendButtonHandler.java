
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendButtonHandler implements ActionListener {

    private Gui gui;

    public SendButtonHandler(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UdpConnectionHandler.send(gui);
        } catch (Exception e1) {
            gui.writeToConsol("No multicast nor broadcast on");
        }
    }
}
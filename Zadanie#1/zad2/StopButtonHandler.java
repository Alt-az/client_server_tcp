import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopButtonHandler implements ActionListener {

    App app;

    public StopButtonHandler(App app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (app.getCheckBoxs()[i].isSelected()) {
                app.getRunning()[i] = false;
                app.getHelloThreads()[i].suspend();
            }
        }
    }

}

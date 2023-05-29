import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonHandler implements ActionListener {

    App app;

    public StartButtonHandler(App app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (app.getCheckBoxs()[i].isSelected()) {
                app.getRunning()[i] = true;
                app.getHelloThreads()[i].resume();
            }
        }
    }

}

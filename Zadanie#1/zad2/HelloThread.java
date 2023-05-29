import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class HelloThread extends Thread {
    App app;
    int id;

    public HelloThread(App app, int id) {
        this.app = app;
        this.id = id;
    }

    @Override
    public void run() {
        Thread.currentThread().suspend();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        app.getPane().setCharacterAttributes(attributeSet, true);

        for (int i = 0; i < 26; i++) {

            while (!app.getRunning()[id - 1]) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                String h = Character.toString((char) ('A' + i));
                app.getDoc().insertString(app.getDoc().getLength(), h +
                        (id < 10 ? id : 0) + "\n",
                        attributeSet);
                sleep(1000);
            } catch (BadLocationException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

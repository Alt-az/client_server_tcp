import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Task {
    private App app;
    private int id;
    private ExecutorService executors;
    private Future<Void> future;

    public Task(App app, int id) {
        this.app = app;
        this.id = id;
        this.executors = Executors.newSingleThreadExecutor();
    }

    public ExecutorService getExecutors() {
        return executors;
    }

    public Future<Void> getFuture() {
        return future;
    }

    public Future<Void> display() {
        future = executors.submit(() -> {
            SimpleAttributeSet attributeSet = new SimpleAttributeSet();
            StyleConstants.setBold(attributeSet, true);
            app.getPane().setCharacterAttributes(attributeSet, true);
            for (int i = 0; i < 26; i++) {
                try {
                    String h = Character.toString((char) ('A' + i));
                    app.getDoc().insertString(app.getDoc().getLength(), h +
                            (id < 10 ? id : 0) + "\n",
                            attributeSet);
                    Thread.sleep(1000);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return null;
        });
        return future;
    }

}

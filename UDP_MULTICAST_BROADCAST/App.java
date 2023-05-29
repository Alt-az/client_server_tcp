import javax.swing.text.BadLocationException;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            new Gui();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

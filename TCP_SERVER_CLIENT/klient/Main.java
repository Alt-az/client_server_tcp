
import javax.swing.text.BadLocationException;

public class Main {
    public static void main(String argv[]) {
        try {
            new Gui();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}

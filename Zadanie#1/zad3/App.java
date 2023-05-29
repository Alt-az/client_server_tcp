import javax.swing.*;
import javax.swing.text.Document;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class App extends JFrame {

    private JFrame frame;
    private JTextPane pane;
    private JScrollPane scrollPane;
    private Document doc;
    private GridBagConstraints c;
    private HelloThread[] helloThreads;
    public Object lock;

    public App() {
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        pane = new JTextPane();
        scrollPane = new JScrollPane(pane);
        helloThreads = new HelloThread[10];
        lock = new Object();

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        c = new GridBagConstraints();

        doc = pane.getStyledDocument();

        Container cp = frame.getContentPane();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 11;
        c.gridwidth = 1;
        c.ipadx = 200;
        c.ipady = 330;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        cp.add(scrollPane, c);

        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        for (int i = 0; i < 10; i++) {
            helloThreads[i] = new HelloThread(this, i + 1);
            helloThreads[i].start();
        }
    }

    public Document getDoc() {
        return doc;
    }

    public JTextPane getPane() {
        return pane;
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}

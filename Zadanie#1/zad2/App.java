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
    private JButton start;
    private JButton stop;
    private GridBagConstraints c;
    private JCheckBox[] checkBoxs;
    private HelloThread[] helloThreads;
    private boolean[] running;

    public App() {
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        pane = new JTextPane();
        scrollPane = new JScrollPane(pane);
        
        checkBoxs = new JCheckBox[10];
        helloThreads = new HelloThread[10];
        running = new boolean[10];

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        c = new GridBagConstraints();
        start = new JButton("Start");
        stop = new JButton("Stop");

        doc = pane.getStyledDocument();

        start.addActionListener(new StartButtonHandler(this));
        stop.addActionListener(new StopButtonHandler(this));

        Container cp = frame.getContentPane();
        c.gridy = 0;
        c.gridx = 0;
        cp.add(start, c);

        c.gridy = 0;
        c.gridx = 1;
        cp.add(stop, c);

        for (int i = 0; i < 10; i++) {
            c.gridy = i + 1;
            c.gridx = 1;
            checkBoxs[i] = new JCheckBox("Watek" + Integer.toString(i + 1));
            cp.add(checkBoxs[i], c);
        }

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

    
    public boolean[] getRunning() {
        return running;
    }


    
    public HelloThread[] getHelloThreads() {
        return helloThreads;
    }

    public JCheckBox[] getCheckBoxs() {
        return checkBoxs;
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}


import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

public class Gui {
    private JFrame frame;
    private JScrollPane scrollPane;
    private JTextPane pane;
    private Document doc;
    private Server server;
    private final int WIDTH = 600;
    private final int HEIGHT = 500;
    private JLabel status;
    private JButton conButton;
    private JTextField port;
    private int numberOfClients;

    public Gui() throws BadLocationException {
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        pane = new JTextPane();
        server = new Server();
        port = new JTextField("7");
        status = new JLabel("<html>#1 no connection <br/> #2 no connection <br/> #3 no connection <br/></html>");
        numberOfClients = 0;
        GridBagConstraints c = new GridBagConstraints();

        scrollPane = new JScrollPane(pane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        conButton = new JButton("Start");

        conButton.addActionListener(new ConnectButtonHandler(this));

        Container cp = frame.getContentPane();
        doc = pane.getStyledDocument();

        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;

        cp.add(port, c);
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        cp.add(conButton, c);

        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 0.15;
        c.weighty = 0.15;
        c.gridx = 4;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        cp.add(status, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        c.gridwidth = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        cp.add(scrollPane, c);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public synchronized void updateStatus() {
        status
                .setText("<html>#1 " + server.clientsInfo[0] + " <br/> #2 "
                        + server.clientsInfo[1] + " <br/> #3 " + server.clientsInfo[2]
                        + " <br/></html>");

    }

    public synchronized void writeToConsol(String message) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        try {
            getDoc().insertString(getDoc().getLength(), message + '\n', attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public JButton getConButton() {
        return conButton;
    }

    public Server getServer() {
        return server;
    }

    public JLabel getStatus() {
        return status;
    }

    public JTextField getPort() {
        return port;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public Document getDoc() {
        return doc;
    }

}

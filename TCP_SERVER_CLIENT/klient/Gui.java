
import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

public class Gui {
    private JFrame frame;
    private JScrollPane scrollPane;
    private JTextPane pane;
    private Document doc;
    private Client klient;
    private final int WIDTH = 600;
    private final int HEIGHT = 500;
    private JTextField message;
    private JTextField ip;
    private JTextField port;
    private JLabel status;
    private GridBagConstraints c;
    private JButton conButton;
    private JButton send;

    public Gui() throws BadLocationException {
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        pane = new JTextPane();
        scrollPane = new JScrollPane(pane);
        klient = new Client();

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        message = new JTextField();
        ip = new JTextField("localhost");
        port = new JTextField("7");
        status = new JLabel("no connection");
        c = new GridBagConstraints();
        conButton = new JButton("Connect");
        send = new JButton("Send");

        doc = pane.getStyledDocument();

        conButton.addActionListener(new ConnectionButtonHandler(klient, this));
        send.addActionListener(new SendButtonHandler(klient, this));

        Container cp = frame.getContentPane();

        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        cp.add(conButton, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        cp.add(message, c);

        c.gridwidth = 1;
        c.gridheight = 2;
        c.gridx = 4;
        c.gridy = 0;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.NONE;
        cp.add(status, c);

        c.gridheight = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.fill = GridBagConstraints.HORIZONTAL;
        cp.add(send, c);

        c.gridx = 0;
        c.gridy = 0;
        cp.add(ip, c);

        c.gridx = 1;
        c.gridy = 0;
        cp.add(port, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 5;
        c.ipadx = 200;
        c.ipady = 330;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        cp.add(scrollPane, c);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void writeToConsol(String message) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        getPane().setCharacterAttributes(attributeSet, true);
        try {
            getDoc().insertString(getDoc().getLength(), message + '\n', attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextPane getPane() {
        return pane;
    }

    public Document getDoc() {
        return doc;
    }

    public Client getKlient() {
        return klient;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public JTextField getMessage() {
        return message;
    }

    public JTextField getIp() {
        return ip;
    }

    public JTextField getPort() {
        return port;
    }

    public JLabel getStatus() {
        return status;
    }

    public GridBagConstraints getC() {
        return c;
    }

    public JButton getConButton() {
        return conButton;
    }

    public JButton getSend() {
        return send;
    }

}

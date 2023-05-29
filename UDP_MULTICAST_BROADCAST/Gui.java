
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.*;
import javax.swing.text.*;

public class Gui {
    private JFrame frame;
    private JScrollPane scrollPane;
    private JTextPane pane;
    private Document doc;
    private final int WIDTH = 600;
    private final int HEIGHT = 500;
    private JTextField message;
    private JTextField ip;
    private JTextField port;
    private GridBagConstraints c;
    private JButton listenButton;
    private JButton send;
    private JCheckBox broadcast;

    public Gui() throws BadLocationException {
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        pane = new JTextPane();
        scrollPane = new JScrollPane(pane);
        broadcast = new JCheckBox("Send a message in broadcast mode");

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        message = new JTextField();
        ip = new JTextField("224.0.0.10");
        port = new JTextField("7");
        c = new GridBagConstraints();
        listenButton = new JButton("Start listening");
        send = new JButton("Send");

        doc = pane.getStyledDocument();
        broadcast.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (broadcast.isSelected()) {
                    try {
                        UdpConnection.datagramSocket = new DatagramSocket();
                        UdpConnection.datagramSocket.setBroadcast(true);
                        UdpConnection.datagramSocket.setReuseAddress(true);
                    } catch (NumberFormatException e1) {
                        e1.printStackTrace();
                    } catch (SocketException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    UdpConnection.datagramSocket.close();
                }
            }

        });
        listenButton.addActionListener(new ListenButtonHandler(this));
        send.addActionListener(new SendButtonHandler(this));

        Container cp = frame.getContentPane();

        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        cp.add(listenButton, c);

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
        cp.add(broadcast, c);

        c.gridx = 0;
        c.gridy = 3;
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

    public GridBagConstraints getC() {
        return c;
    }

    public JButton getListenButton() {
        return listenButton;
    }

    public JButton getSend() {
        return send;
    }

    public JCheckBox getBroadcast() {
        return broadcast;
    }

}

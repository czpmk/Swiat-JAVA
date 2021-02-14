package labO.world.gui.prompts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPrompt extends JFrame implements ActionListener {
    private final JLabel text = new JLabel();
    private final JButton okButton = new JButton("OK");

    public InfoPrompt(String information) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - 130, point.y - 50);
        this.setTitle("Info!");
        text.setText(information);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(text);
        add(okButton);
        okButton.setMaximumSize(new Dimension(250, 40));
        okButton.addActionListener(this);
        setPreferredSize(new Dimension(250, 100));
        pack();
        setResizable(false);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == okButton) {
            dispose();
        }
    }
}

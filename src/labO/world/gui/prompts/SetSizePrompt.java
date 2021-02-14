package labO.world.gui.prompts;

import labO.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetSizePrompt extends JFrame implements ActionListener {
    private final World world_;
    private final JLabel info1 = new JLabel("Prosze podać wymiary świata - w zakresie [2, 25]");
    private final JLabel xText = new JLabel("wysokość  :");
    private final JLabel yText = new JLabel("szerokość :");
    private final JTextField xField = new JTextField();
    private final JTextField yField = new JTextField();
    private final JButton okButton = new JButton("Ustaw rozmiar");

    public SetSizePrompt(World world) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - 200, point.y - 70);
        this.setTitle("Ustawianie rozmiaru planszy");
        world_ = world;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        addComponents();
        setPreferredSize(new Dimension(400, 130));
        pack();
        setResizable(false);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == okButton) {
            try {
                int newX = Integer.parseInt(xField.getText());
                int newY = Integer.parseInt(yField.getText());
                if (newX >= 2 && newX <= 25) {
                    if (newY >= 2 && newY <= 25) {
                        world_.resize(newX, newY);
                        dispose();
                    }
                }
            } catch (NumberFormatException e) {
                // nie rob nic
            }
        }
    }

    private void addComponents() {
        add(info1);
        JPanel xLine = new JPanel();
        xLine.setLayout(new BoxLayout(xLine, BoxLayout.LINE_AXIS));
        xLine.add(xText);
        xLine.add(xField);
        add(xLine);
        JPanel yLine = new JPanel();
        yLine.setLayout(new BoxLayout(yLine, BoxLayout.LINE_AXIS));
        yLine.add(yText);
        yLine.add(yField);
        add(yLine);
        add(okButton);
        okButton.addActionListener(this);
    }
}

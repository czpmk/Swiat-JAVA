package labO.world.gui.prompts;

import labO.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaceRandomOrganismsPrompt extends JFrame implements ActionListener {
    private final World world_;
    private final JLabel info1 = new JLabel("Ile organizmów umieścić - od 0 do 50");
    private final JLabel sizeText = new JLabel("ilość : ");
    private final JTextField sizeField = new JTextField();
    private final JButton okButton = new JButton("Umieść");

    public PlaceRandomOrganismsPrompt(World world) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - 200, point.y - 60);
        this.setTitle("Dodawanie organizmów");
        world_ = world;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        addComponents();
        setPreferredSize(new Dimension(400, 110));
        pack();
        setResizable(false);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == okButton) {
            try {
                int numberOfOrganisms = Integer.parseInt(sizeField.getText());
                if (numberOfOrganisms >= 0 && numberOfOrganisms <= 50) {
                    world_.placeRandomOrganisms(numberOfOrganisms);
                    dispose();
                }
            } catch (NumberFormatException e) {
                // nie rob nic
            }
        }
    }

    private void addComponents() {
        add(info1);
        JPanel sizeLine = new JPanel();
        sizeLine.setLayout(new BoxLayout(sizeLine, BoxLayout.LINE_AXIS));
        sizeLine.add(sizeText);
        sizeLine.add(sizeField);
        add(sizeLine);
        add(okButton);
        okButton.addActionListener(this);
    }
}

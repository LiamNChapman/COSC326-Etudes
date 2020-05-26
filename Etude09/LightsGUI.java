import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.util.ArrayList;

public class LightsGUI extends JPanel{
    private JPanel lightsPanel = new JPanel();
    private JTextField lightsField = new JTextField(10);
    private JTextField connectionsField = new JTextField(10);
    private JButton createButton = new JButton("Create Lights");
    private JButton solveButton = new JButton("Solve");
    private JButton resetButton = new JButton("Reset");


    public static void main(String[] args){
        JFrame lights = new JFrame();
        lights.getContentPane().add(new LightsGUI());
        lights.setDefaultCloseOperation(3);
        lights.setSize(new Dimension(500, 500));
        lights.pack();
        lights.setVisible(true);
    }

    public LightsGUI(){
        JPanel inputPanel = new JPanel();
        JLabel lightsFieldLabel = new JLabel("Lights: ");
        JLabel connectionsFieldLabel = new JLabel("Connections: ");
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(250, 500));
        //inputPanel.setLayout(new GridLayout(4, 2, 125, 125));
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(lightsFieldLabel);
        //lightsField.setLocation(150, 300);
        inputPanel.add(lightsField);
        inputPanel.add(connectionsFieldLabel);
        inputPanel.add(connectionsField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(createButton);
        inputPanel.add(solveButton);
        resetButton.setSize(new Dimension(100,100));
        inputPanel.add(resetButton);
        add(inputPanel);

        

        lightsPanel.setBackground(Color.WHITE);
        //lightsPanel.getContentPanel().setLayout(null);
        //lightsPanel.setBounds(105, 105, 395, 395);
        lightsPanel.setPreferredSize(new Dimension(500, 500));
        //lightsPanel.setLocation(105, 105);
        add(lightsPanel);

    }
}
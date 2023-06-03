package smartbox;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ContainerPanel extends AppPanel {

    public ContainerPanel(AppFactory factory) {
        super(factory);
        String[] buttonNames = new String[]{"Add", "Rem", "Run"};
        controlPanel.setLayout(new GridLayout(3, 1));
        setBackground(Color.green);
        for (String s :
                buttonNames) {
            JPanel tempPanel = new JPanel();
            JButton temp = new JButton(s);
            temp.addActionListener(this);
            tempPanel.add(temp);
            view.setBackground(tempPanel.getBackground());
            controlPanel.add(tempPanel);
        }
        view.setBorder(new LineBorder(view.getBackground(), 0));
    }

    public static void main(String[] args) {
        AppFactory factory = new ContainerFactory();
        AppPanel panel = new ContainerPanel(factory);
        panel.display();
    }
}

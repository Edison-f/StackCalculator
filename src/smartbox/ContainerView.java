package smartbox;

import mvc.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;

public class ContainerView extends View {

    private java.awt.List components;

    public ContainerView(Model model) {
        super(model);
        components = new java.awt.List(10);
        this.add(components);
    }

    public void propertyChange(PropertyChangeEvent arg0) {
        Container container = (Container) model;
        components.removeAll();
        for (Component c : container.getComponents()) {
            components.add(c.toString());
        }
        super.propertyChange(arg0);
    }
}
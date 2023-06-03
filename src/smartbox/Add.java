package smartbox;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class Add extends Command {
    public Add(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Container container = (Container) model;
        String s = Utilities.ask("Class Name");
        container.addComponent(s);
    }
}

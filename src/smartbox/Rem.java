package smartbox;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class Rem extends Command {
    public Rem(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Container container = (Container) model;
        String s = Utilities.ask("Name");
        container.remComponent(s);
    }
}

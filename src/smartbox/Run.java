package smartbox;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class Run extends Command {

    public Run(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Container container = (Container) model;
        String s = Utilities.ask("Name");
        container.launch(s);
    }
}

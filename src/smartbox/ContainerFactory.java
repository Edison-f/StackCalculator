package smartbox;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class ContainerFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Container();
    }

    @Override
    public View makeView(Model model) {
        return new ContainerView((Container) model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Add", "Rem", "Run"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return switch (type) {
            case "Add" -> new Add(model);
            case "Rem" -> new Rem(model);
            case "Run" -> new Run(model);
            default -> null;
        };
    }

    @Override
    public String getTitle() {
        return "SmartBox";
    }

    @Override
    public String[] getHelp() {
        return new String[0];
    }

    @Override
    public String about() {
        return null;
    }
}

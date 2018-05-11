package cs.console.command_pattern;

import cs.controller.Controller;

public class ShowInventory implements Command {

    @Override
    public void execute(Controller controller) {
        controller.showInventory();
    }

}

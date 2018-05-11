package cs.console.command_pattern;

import cs.controller.Controller;

public interface Command {

    void execute(Controller controller);

}

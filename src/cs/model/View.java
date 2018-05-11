package cs.model;

import cs.controller.Controller;

public interface View extends Observer {

    void setController(Controller controller);

    void init();

}

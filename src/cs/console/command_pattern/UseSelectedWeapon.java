package cs.console.command_pattern;

import cs.controller.Controller;

import java.util.Scanner;

public class UseSelectedWeapon implements Command {

    @Override
    public void execute(Controller controller) {
        boolean loop_terminate = true;
        do {
            System.out.println("----------------------------");
            System.out.println(
                    "1. Attack" + "\n2. Look at weapon"
                            + "\n3. Hide to inventory" + "\n4. Return to main menu"
            );

            System.out.print("Option: ");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    controller.attack();
                    break;
                case 2:
                    controller.lookAtWeapon();
                    break;
                case 3:
                    controller.hideWeapon(controller.getCurrentWeapon());
                    loop_terminate = false;
                    break;
                case 4:
                    loop_terminate = false;
            }
        } while (loop_terminate);
    }

}

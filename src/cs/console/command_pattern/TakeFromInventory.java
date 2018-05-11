package cs.console.command_pattern;

import cs.controller.Controller;

import java.util.Scanner;

public class TakeFromInventory implements Command {

    @Override
    public void execute(Controller controller) {

        System.out.println("----------------------------");
        System.out.println("(99) - return to main menu");
        System.out.println("Choose weapon to take: ");
        controller.showInventory();
        Scanner in = new Scanner(System.in);
        System.out.print("Take number: ");
        int choice = in.nextInt();
        if(choice != 99) controller.takeFromInventory(choice);
        System.out.println("----------------------------");

    }

}

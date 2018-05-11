package cs.console;

import cs.console.command_pattern.Command;
import cs.console.command_pattern.ShowInventory;
import cs.console.command_pattern.TakeFromInventory;
import cs.console.command_pattern.UseSelectedWeapon;
import cs.controller.Controller;
import cs.model.Inventory;
import cs.model.View;
import cs.model.weapons.Weapon;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements View {

    private Controller controller;
    private Map<Integer, String> weaponList;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void init() {

        weaponList = new HashMap<>();
        Command[] command = new Command[3];
        command[0] = new ShowInventory();
        command[1] = new TakeFromInventory();
        command[2] = new UseSelectedWeapon();


        do {
            System.out.println("----------------------------");
            System.out.println(
                    "1. Show inventory\n" + "2. Take from inventory\n" +
                            "3. Use selected weapon\n" + "4. Exit"
            );
            System.out.print("Option: ");

            try {
                Scanner in = new Scanner(System.in);
                int choice = in.nextInt();
                if (choice == 4) break;
                command[choice - 1].execute(controller);
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Choose correct menu position!");
            } catch (NullPointerException e) {
                System.out.println("Select weapon first!");
            }
        } while (true);

    }

    @Override
    public void onAttack() {
        System.out.println("----------------------------");
        System.out.println("You're using a " + controller.getCurrentWeapon());
        System.out.println("----------------------------");
    }

    @Override
    public void onLook() {
        System.out.println("----------------------------");
        System.out.println("You're looking at " + controller.getCurrentWeapon());
        System.out.println("----------------------------");
    }

    @Override
    public void onShowInventory(Inventory inventory) {
        System.out.println("----------------------------");
        weaponList.clear();
        for (Weapon weapon : inventory.getWeapons().values()) {
            weaponList.put(weapon.getID(), weapon.toString());
        }
        for (Map.Entry<Integer, String> wpn : weaponList.entrySet()) {
            System.out.println(wpn.getKey() + ". " + wpn.getValue());
        }
        System.out.println("----------------------------");
    }

    @Override
    public void onTakeFromInventory(Inventory inventory, Integer weaponId) {
        if(controller.getCurrentWeapon() == null) {
            if (inventory.getWeapons().keySet().contains(weaponId)) {
                inventory.delete(weaponId);
                weaponList.remove(weaponId);
                controller.setCurrentWeapon(weaponId);
            } else {
                System.out.println("Choose existing weapon!");
            }
        } else {
            controller.swapWeapon(weaponId);
        }
    }

    @Override
    public void onHideToInventory(Inventory inventory, Weapon weapon) {
        inventory.add(weapon);
        weaponList.put(weapon.getID(), weapon.toString());
        controller.setCurrentWeapon(99);
    }

    @Override
    public void onSwap(Integer weaponId) {
        controller.hideWeapon(controller.getCurrentWeapon());
        controller.takeFromInventory(weaponId);
    }

}


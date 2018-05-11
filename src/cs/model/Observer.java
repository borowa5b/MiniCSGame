package cs.model;

import cs.model.weapons.Weapon;

public interface Observer {

    void onAttack();
    void onLook();
    void onShowInventory(Inventory inventory);
    void onTakeFromInventory(Inventory inventory, Integer weaponId);
    void onHideToInventory(Inventory inventory, Weapon weapon);
    void onSwap(Integer weaponId);

}

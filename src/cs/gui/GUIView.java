package cs.gui;

import cs.controller.Controller;
import cs.model.Inventory;
import cs.model.View;
import cs.model.weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GUIView extends JFrame implements View {

    private Controller controller;
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 400;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private JFrame frame;
    private JTextArea mainText, currentWeaponText;
    private JPanel invPanel;
    private JButton btnShowInv, btnAttack, btnLookAt, btnHide;
    private Map<Integer, JButton> weaponBtnList;

    /**
     * Launch the application.
     */
    @Override
    public void init() {
        EventQueue.invokeLater(() -> {
            try {
                GUIView.this.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    GUIView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        //init
        invPanel = new JPanel();
        weaponBtnList = new HashMap<>();
        btnShowInv = new JButton("Show inventory");
        btnAttack = new JButton("Attack");
        btnLookAt = new JButton("Look at weapon");
        btnHide = new JButton("Hide weapon");

        //frame
        frame = new JFrame();
        frame.setTitle("Some random game");
        frame.setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //panels
        JPanel bottomPanel = new JPanel();
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JPanel btnPanel = new JPanel();
        bottomPanel.add(btnPanel);

        JPanel currentWeaponPanel = new JPanel();
        bottomPanel.add(currentWeaponPanel);

        JPanel infoPanel = new JPanel();
        frame.getContentPane().add(infoPanel, BorderLayout.NORTH);

        //buttons
        btnShowInv.addActionListener(e -> controller.showInventory());
        btnPanel.add(btnShowInv);

        btnAttack.addActionListener(e -> controller.attack());
        btnPanel.add(btnAttack);

        btnLookAt.addActionListener(e -> controller.lookAtWeapon());
        btnPanel.add(btnLookAt);

        btnHide.addActionListener(e -> controller.hideWeapon(controller.getCurrentWeapon()));
        btnPanel.add(btnHide);

        //text areas
        mainText = new JTextArea();
        mainText.setEditable(false);
        mainText.setOpaque(false);
        mainText.setText("...");
        infoPanel.add(mainText);

        currentWeaponText = new JTextArea("Current weapon: Knife");
        currentWeaponText.setEditable(false);
        currentWeaponText.setOpaque(false);
        currentWeaponPanel.add(currentWeaponText);
    }

    //callback things
    @Override
    public void onAttack() {
        mainText.setText("You're using a " + controller.getCurrentWeapon());
    }

    @Override
    public void onLook() {
        mainText.setText("You're looking at " + controller.getCurrentWeapon());
    }

    @Override
    public void onShowInventory(Inventory inventory) {

        if (invPanel.getParent() == null) {
            btnShowInv.setText("Hide inventory");
            invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS));
            frame.add(invPanel, BorderLayout.WEST);

            redrawInventoryPanel(inventory);
        } else {
            btnShowInv.setText("Show inventory");
            invPanel.removeAll();
            frame.remove(invPanel);
            frame.revalidate();
            frame.repaint();
        }

    }

    private void redrawInventoryPanel(Inventory inventory) {
        invPanel.removeAll();
        weaponBtnList.clear();
        for (Weapon weapon : inventory.getWeapons().values()) {
            JButton tempWeaponBtn = new JButton(weapon.toString());
            tempWeaponBtn.setMaximumSize(new Dimension(100, 50));
            tempWeaponBtn.addActionListener(e -> controller.takeFromInventory(weapon.getID()));
            weaponBtnList.put(weapon.getID(), tempWeaponBtn);
        }

        for (JButton btn : weaponBtnList.values()) {
            invPanel.add(btn);
        }
        invPanel.revalidate();
        invPanel.repaint();
    }

    @Override
    public void onTakeFromInventory(Inventory inventory, Integer weaponId) {
        if (controller.getCurrentWeapon() != null) {
            controller.swapWeapon(weaponId);
        } else {
            inventory.delete(weaponId);
            weaponBtnList.remove(weaponId);
            mainText.setText("You've taken weapon");
            controller.setCurrentWeapon(weaponId);
            currentWeaponText.setText("Current weapon: " + controller.getCurrentWeapon());
            btnHide.setVisible(true);
            btnLookAt.setVisible(true);
            btnAttack.setVisible(true);
            redrawInventoryPanel(inventory);
        }
    }

    @Override
    public void onHideToInventory(Inventory inventory, Weapon weapon) {
        inventory.add(weapon);
        controller.setCurrentWeapon(99);
        currentWeaponText.setText("Current weapon: none");
        btnHide.setVisible(false);
        btnLookAt.setVisible(false);
        btnAttack.setVisible(false);
        redrawInventoryPanel(inventory);
    }

    @Override
    public void onSwap(Integer weaponId) {
        controller.hideWeapon(controller.getCurrentWeapon());
        controller.takeFromInventory(weaponId);
    }

}

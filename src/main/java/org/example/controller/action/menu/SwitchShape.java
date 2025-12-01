package org.example.controller.action.menu;

import org.example.model.shape.factory.ShapeType;

import javax.swing.*;

public class SwitchShape implements AppCommand {
    private final MenuState menuState;
    private final ShapeType selectedType;

    private JRadioButtonMenuItem shapeButton;

    public SwitchShape(MenuState menuState, ShapeType selectedType, JRadioButtonMenuItem shapeButton) {
        this.menuState = menuState;
        this.selectedType = selectedType;
        this.shapeButton = shapeButton;
    }

    @Override
    public void execute() {
        //shapeButton.setSelected(true);
        menuState.setSelectedShape(selectedType);
    }
}

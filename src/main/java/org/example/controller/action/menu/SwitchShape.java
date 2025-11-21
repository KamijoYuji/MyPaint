package org.example.controller.action.menu;

import org.example.model.shape.factory.ShapeType;

import javax.swing.*;

public class SwitchShape implements AppCommand{
    private MenuState menuState;
    private ShapeType selectedType;

    public SwitchShape(MenuState menuState, ShapeType selectedType) {
        this.menuState = menuState;
        this.selectedType = selectedType;
    }

    @Override
    public void execute() {
        menuState.setSelectedShape(selectedType);
    }
}

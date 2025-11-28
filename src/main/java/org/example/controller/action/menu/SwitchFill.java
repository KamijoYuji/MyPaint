package org.example.controller.action.menu;

import org.example.model.shape.fill.FillBehavior;

public class SwitchFill implements AppCommand{
    private MenuState menuState;
    private FillBehavior fillBehavior;

    public SwitchFill(MenuState menuState, FillBehavior fillBehavior) {
        this.menuState = menuState;
        this.fillBehavior = fillBehavior;
    }

    @Override
    public void execute() {
        menuState.setFill(fillBehavior);
    }
}

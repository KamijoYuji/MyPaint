package org.example.controller.action.menu;

import org.example.controller.action.AppAction;

public class SwitchAction implements AppCommand{
    private MenuState menuState;
    private AppAction appAction;

    public SwitchAction(MenuState menuState, AppAction appAction) {
        this.menuState = menuState;
        this.appAction = appAction;
    }

    @Override
    public void execute() {
        menuState.setAppAction(appAction);
    }
}
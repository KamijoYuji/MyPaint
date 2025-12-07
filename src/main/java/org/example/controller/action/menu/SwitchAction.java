package org.example.controller.action.menu;

import org.example.controller.Controller;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;

public class SwitchAction implements AppCommand {
    private final MenuState menuState;
    private final AppAction appAction;
    private final Controller controller;

    public SwitchAction(Controller controller, AppAction appAction) {
        this.controller = controller;
        this.menuState = controller.getMenuCreator().getMenuState();
        this.appAction = appAction;
    }

    @Override
    public void execute() {
        menuState.setAppAction(appAction);
        if (controller != null) {
            if (appAction instanceof ActionDraw) {
                controller.setDrawingAction();
            } else {
                controller.setMovingAction();
            }
        }
    }
}
package org.example.controller.action.menu;

import org.example.controller.state.UndoMachine;

public class SwitchRedo implements AppCommand{
    private UndoMachine undoMachine;

    @Override
    public void execute() {
        undoMachine.executeRedo();
        undoMachine.updateButtons();
    }
}

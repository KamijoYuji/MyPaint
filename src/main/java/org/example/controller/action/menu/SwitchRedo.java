package org.example.controller.action.menu;

import org.example.controller.action.state.UndoMachine;

public class SwitchRedo implements AppCommand {
    private UndoMachine undoMachine;

    public SwitchRedo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    @Override
    public void execute(){
        undoMachine.executeRedo();
        undoMachine.updateButtons();
    }
}
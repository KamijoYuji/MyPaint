package org.example.controller.action.menu;

import org.example.controller.action.state.UndoMachine;

public class SwitchUndo implements AppCommand {
    private UndoMachine undoMachine;
    public SwitchUndo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    @Override
    public void execute(){
        undoMachine.executeUndo();
        undoMachine.updateButtons();
    }
}
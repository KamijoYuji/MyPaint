package org.example.controller.state;

import org.example.controller.action.AppAction;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void updateButtons(){
        //TODO:undoActionListener

        //undoActionListener.setEnabled(isEnableUndo());
        //undoActionListener.setEnabled(isEnableRedo());
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);
        //TODO: Определить переход по состоянию
        //undoRedoState = ;
    }
}

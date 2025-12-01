package org.example.controller.action.menu;

import javax.swing.*;
import java.awt.*;

public class SwitchColor implements AppCommand {
    private final MenuState menuState;
    private final boolean useDefault;
    private final Color defaultColor;
    private final JRadioButtonMenuItem radioButton;

    public SwitchColor(MenuState menuState, boolean useDefault, Color defaultColor, JRadioButtonMenuItem radioButton) {
        this.menuState = menuState;
        this.useDefault = useDefault;
        this.defaultColor = defaultColor;
        this.radioButton = radioButton;
    }

    @Override
    public void execute() {
        radioButton.setSelected(!useDefault);
        Color color = useDefault
                ? defaultColor
                : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        menuState.setColor(color);
    }

}

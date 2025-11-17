package org.example.controller.action.menu;

import javax.swing.*;
import java.awt.*;

public class SwitchColor implements AppCommand{
    private MenuState menuState;
    private boolean useDefault;
    private Color defaultColor;
    private JRadioButtonMenuItem radioButton;
    @Override
    public void execute() {
        radioButton.setSelected(!useDefault);
        Color color = useDefault
                ? defaultColor
                : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        menuState.setColor(color);
    }

}

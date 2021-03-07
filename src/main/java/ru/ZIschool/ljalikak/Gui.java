package ru.ZIschool.ljalikak;

import ru.ZIschool.ljalikak.controller.ControllerFrame;
import ru.ZIschool.ljalikak.view.MyRegistrationFrame;

public class Gui {

    public static void main(String[] args) {
        ControllerFrame controller = new ControllerFrame();
        MyRegistrationFrame f = new MyRegistrationFrame(controller);
    }
}

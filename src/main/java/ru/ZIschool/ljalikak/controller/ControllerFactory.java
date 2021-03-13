package ru.ZIschool.ljalikak.controller;

public abstract class ControllerFactory {

    public static IController newController(String type) {
        IController controller;

        switch (type) {
            case "console":
                controller = new ControllerConsole();
                break;
            case "gui":
                controller = new ControllerGUI();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return controller;
    }
}

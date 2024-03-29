package ru.zl.school.ljalikak.controller;

public enum Actions {
    NOTHING,
    ENTER,

    EXIT,

    MOVE_LEFT,
    MOVE_RIGHT,
    MOVE_UP,
    MOVE_DOWN;

    public static Actions getAction(char chr) {
        switch (chr) {
            case 'w' : return MOVE_UP;
            case 's':  return MOVE_DOWN;
            case 'a': return MOVE_LEFT;
            case 'd': return MOVE_RIGHT;
            case '\n': return ENTER;
            case 'q': return EXIT;
            default:
                return NOTHING;
        }
    }

    public static Actions getAction(String line) {
        switch (line) {
            case "up" :
            case "^":
            case "south":
            case "w": return MOVE_UP;
            case "down" :
            case "north":
            case "s": return MOVE_DOWN;
            case "left" :
            case "<":
            case "west":
            case "a": return MOVE_LEFT;
            case "right" :
            case ">":
            case "east":
            case "d": return MOVE_RIGHT;
            case "text":
            case "talk": return ENTER;
            case "q":
            case "e":
            case "quit":
            case "exit": return EXIT;
            default:
                return NOTHING;
        }
    }
}

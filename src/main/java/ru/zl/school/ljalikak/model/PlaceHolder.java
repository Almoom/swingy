package ru.zl.school.ljalikak.model;

import java.awt.*;
import java.io.Serializable;

public class PlaceHolder implements Serializable {
    public Types type;
    public Point pos;

    public PlaceHolder(Types type) {
        this.type = type;
        pos = new Point(-1, -1);
    }

    public PlaceHolder(Types type, int x, int y) {
        this.type = type;
        pos = new Point(x, y);
    }

    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }

    public void setXY(int x, int y) {
        pos.x = x;
        pos.y = y;
    }
    public Point getPos() {
        return pos;
    }

    public Types getTypes() {
        return type;
    }
}

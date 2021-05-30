package ru.zl.school.ljalikak.view;

import ru.zl.school.ljalikak.model.Place;
import ru.zl.school.ljalikak.model.PlaceHolder;
import ru.zl.school.ljalikak.model.Types;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MyPanel extends JPanel {
    private final static int CELL_SIZE = 30;
    private static final Color GREEN_COLOR = new Color(18, 53, 36);
    private static final Place OUT = new Place(new PlaceHolder(Types.BOUNDARY), Types.BLACK);

    private Place[][] env;
    private TexturePaint rad;
    private TexturePaint enemy;
    private TexturePaint player;
    private TexturePaint rock;

    public MyPanel(int width, int height) throws IOException {
        width = width / CELL_SIZE;
        height = height / CELL_SIZE;
        env = new Place[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                env[i][j] = OUT;
            }
        }
        load();
    }

    private void load() throws IOException {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/rad.png"));
            rad = new TexturePaint(image, new Rectangle(0, 0, 30, 30));
            image = ImageIO.read(getClass().getResource("/rock.jpeg"));
            rock = new TexturePaint(image, new Rectangle(0, 0, 30, 30));
            image = ImageIO.read(getClass().getResource("/pb.png"));
            player = new TexturePaint(image, new Rectangle(0, 0, 30, 30));
            image = ImageIO.read(getClass().getResource("/enemy.png"));
            enemy = new TexturePaint(image, new Rectangle(0, 0, 30, 30));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not load images", "Error", JOptionPane.ERROR_MESSAGE);
            throw ex;
        }
    }

    public Place[][] getEnv() {
        return env;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env[0].length; j++) {
                printPlane(g2, env[i][j], j * CELL_SIZE, i * CELL_SIZE);
                printObject(g2, env[i][j], j * CELL_SIZE, i * CELL_SIZE);
            }
        }
        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env[0].length; j++) {
                System.out.print(env[i][j].getObject().type.toString().substring(0, 3) + " ");
            }
            System.out.println();
        } //todo
    }


    private void printPlane(Graphics2D g2, Place place, int x, int y) {
        int size = 2;

        switch (place.getType()) {
            case GREEN: g2.setColor(GREEN_COLOR); break;
            case BLACK: g2.setColor(Color.black); break;
            default:
                g2.setColor(Color.white);
        }
        g2.drawRect(x + size / 2, y + size / 2, CELL_SIZE - size, CELL_SIZE - size);
        g2.fillRect(x + size / 2, y + size / 2, CELL_SIZE - size, CELL_SIZE - size);
    }

    private void printObject(Graphics2D g2, Place place, int x, int y) {
        int size = 2;

        switch (place.getObject().getTypes()) {
            case STONE: {
                g2.setPaint(rock);
                g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                return;
            }
            case PLAYER: {
                g2.setPaint(player);
                g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                return;
            }
            case ANIMAL: {
                g2.setPaint(enemy);
                g2.fillRect(x + 2, y + 2, 28, 28);
                return;
            }
            case BOUNDARY: g2.setColor(Color.black); break;
            case TREE: {
                g2.setPaint(rad);
                g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                return;
            }
            default:
                break;
        }
        g2.fillOval(x + size / 2, y + size / 2, CELL_SIZE - size, CELL_SIZE - size);
    }
}

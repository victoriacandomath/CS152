import javax.swing.*;
import java.awt.*;

public class Monsters extends Canvas{
    int rows = 5;
    int columns = 5;
    static int screenSize = 500;
    Monster[][] monsters = new Monster[rows][columns];
    Monster monster = new Monster(screenSize/2,screenSize/2,100);

    public static void main(String[] args) {
        Monsters canvas = new Monsters();
        setupScreen(canvas);
    }

    /**
     * Creates the screen to draw the grid
      * @param c the canvas object the screen is on
     */
    static void setupScreen(Canvas c) {
        JFrame frame = new JFrame("Monsters"); //give screen a name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c.setSize(screenSize, screenSize);
        c.setBackground(new Color(0xF6DE90));
        frame.add(c);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Draws the grid of monsters on the screen
     * @param g graphics object to draw on
     */
    void drawMonsters(Graphics g) {

    }

    /**
     * Draws objects on the screen
     * @param g the graphics object to draw on
     */
    public void paint(Graphics g) {
        monster.drawMonster(g);
    }
}

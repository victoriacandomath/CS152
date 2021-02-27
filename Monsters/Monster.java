import java.awt.*;

public class Monster {
    static int totalNumberOfMonsterObjects = 0;
    float size;
    Color monsterColor;
    float xPosition, yPosition;
    Eyes eyes;
    Mouth mouth;
    Name name;

    /**
     * Creates a monster object
     * @param xPositionIn x position of monster
     * @param yPositionIn y position of monster
     * @param sizeIn size of monster
     */
    Monster(float xPositionIn, float yPositionIn, float sizeIn){
        xPosition = xPositionIn;
        yPosition = yPositionIn;
        size = sizeIn;
        int red = ((int) (Math.random()*256));
        int green = ((int) (Math.random()*256));
        int blue = ((int) (Math.random()*256));
        monsterColor = new Color(red, green, blue);
        totalNumberOfMonsterObjects++;

        //creates the eyes and mouth
        eyes = new Eyes(xPosition, yPosition - size/8, size/6);
        mouth = new Mouth(xPosition, yPosition + size/8, size/4);
        name = new Name(xPosition, yPosition + size*5/8, size/5);
    }

    /**
     * Draws monster on the screen
     * @param g graphics object we draw on
     */
    void drawMonster(Graphics g){
        g.setColor(monsterColor);
        g.fillOval((int)xPosition - (int)size/2,(int)yPosition - (int)size/2,(int)size,(int)size);
        eyes.drawEyes(g);
        mouth.drawMouth(g);
        name.drawName(g);
    }
}

class Eyes {
    float size;
    float xPosition, yPosition;

    /**
     * Creates an eyes object
     * @param xPositionIn x position of eyes
     * @param yPositionIn y position of eyes
     * @param sizeIn size of eyes
     */
    Eyes(float xPositionIn, float yPositionIn, float sizeIn){
        size = (int)sizeIn;
        xPosition = (int)xPositionIn;
        yPosition = (int)yPositionIn;
    }

    /**
     * Draws eyes on the screen
     * @param g graphics object we draw on
     */
    void drawEyes(Graphics g){
        int pupilSize = (int) (size/4);
        g.setColor(Color.white);
        g.fillOval(xPosition - size - size/2, yPosition-size/2, size, size);
        g.fillOval(xPosition + size + size/2, yPosition+size/2, size, size);
        g.setColor(Color.black);
        g.fillOval(xPosition - size - pupilSize/2, yPosition-pupilSize/2, pupilSize, pupilSize);
        g.fillOval(xPosition + size + pupilSize/2, yPosition+pupilSize/2, pupilSize, pupilSize);

    }
}

class Mouth {
    float size;
    float xPosition, yPosition;

    /**
     *
     * @param xPositionIn x position of mouth
     * @param yPositionIn y position of mouth
     * @param size size of mouth
     */
    Mouth(float xPositionIn, float yPositionIn, float size){}

    /**
     * Draws mouth to the screen
     * @param g graphics object we draw on
     */
    void drawMouth(Graphics g){}
}

class Name {
    float size;
    float xPosition, yPosition;
    String name;
    final char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    final char[] consonants = {'K', 'T', 'Z', 'R', 'P', 'B', 'L', 'M'};

    /**
     * Creates a name object
     * @param xPositionIn x position of name
     * @param yPositionIn y position of name
     * @param size size of name
     */
    Name(float xPositionIn, float yPositionIn, float size) {

    }

    /**
     * Draws name to the screen
     * @param g the graphics object to draw on
     */
    void drawName(Graphics g) {

    }
}

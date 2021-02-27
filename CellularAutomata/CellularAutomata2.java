import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;

public class CellularAutomata2 extends Canvas {

    final int ALIVE = 1;
    final int DEAD = 0;
    static int screenSize = 700;
    static int cellSize = 10;
    static int arraySize = screenSize/cellSize;
    int[][] currentStates = new int [arraySize][arraySize];
    int[][] nextStates = new int [arraySize][arraySize];
    int delayTime = 500;

    public static void main(String[] args) {
        CellularAutomata2 canvas = new CellularAutomata2();
        setupScreen(canvas);
        canvas.playGame();  //This calls the method myMethod
    }

    public static void setupScreen(CellularAutomata2 canvas) {
        //Creates the screen for your CA
        JFrame frame = new JFrame("Cellular Automata"); //give screen a name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sets the size of the screen
        // See https://docs.oracle.com/javase/9/docs/api/javafx/scene/canvas/Canvas.html
        canvas.setSize(screenSize, screenSize);
        // Sets the background color
        // See https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
        canvas.setBackground(Color.lightGray);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        //frame.setResizable(false);
    }

    /**
     * This method draws things on the screen.
     * This is where you will write
     * code that displays your CA. You do not
     * call this method. It is called automatically.
     * A few sample drawing features are demonstrated below.
     */
    public void paint(Graphics g) {
        for(int i = 0; i < arraySize; i++) {
            for(int j = 0; j < arraySize; j++){
                Color aliveColor = new Color(103, 26, 255);
                Color deadColor = new Color(255, 255, 255);
                if(currentStates[i][j] == 1)
                    g.setColor(aliveColor);
                else
                    g.setColor(deadColor);
                g.fillRect(j*cellSize, i*cellSize,cellSize-1, cellSize-1);
            }
        }
    }

    /**
     * This method includes some useful
     * functionality that you will want
     * to include in your code. Feel free
     * to rename or delete this method
     */
    public void playGame() {
        quarterInitialization();
        //randomInitialization();
        //blinker();

        delay(delayTime);

        while(true) {
            runOneIteration();
        }
    }

    void runOneIteration() {
        //computes rule for all cells, -1 for the out of bounds error
        for (int i = 1; i < arraySize - 1; i++) {
            for (int j = 1; j < arraySize - 1; j++) {
                //nextStates[i][j] = gameOfLifeRule(i, j);
                nextStates[i][j] = flowerRule(i,j);
            }
        }
        //update the currentStates array
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                currentStates[i][j] = nextStates[i][j];
            }
        }

        delay(delayTime*2);

        // The repaint() method redraws your screen.
        // You can use it to refresh your screen after
        // you've updated your CA to its next state
        repaint();
    }

    void randomInitialization() {
        //Initialize states of the array to be random
        for(int i = 0; i < arraySize; i++) {
            for(int j = 0; j < arraySize; j++) {
                currentStates[i][j] = (int)(Math.random()*2);
            }
        }
    }

    void blinker()  {
        //Initialize states of the array
        for(int i = 0; i < arraySize; i++) {
            for(int j = 0; j < arraySize; j++) {
                currentStates[i][j] = DEAD;
            }
        }
        currentStates[arraySize/2 -1][arraySize/2] = ALIVE;
        currentStates[arraySize/2][arraySize/2] = ALIVE;
        currentStates[arraySize/2+1][arraySize/2] = ALIVE;
    }

    void quarterInitialization() {
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                currentStates[i][j] = DEAD;
            }
        }

        //top left quadrant initialization
        currentStates[arraySize/4][arraySize/4] = ALIVE;
        currentStates[arraySize/4-1][arraySize/4-1] = ALIVE;
        currentStates[arraySize/4-1][arraySize/4+1] = ALIVE;
        currentStates[arraySize/4+1][arraySize/4+1] = ALIVE;
        currentStates[arraySize/4+1][arraySize/4-1] = ALIVE;

        //bottom right quadrant initialization
        currentStates[arraySize-arraySize/4][arraySize-arraySize/4] = ALIVE;
        currentStates[arraySize-arraySize/4-1][arraySize-arraySize/4-1] = ALIVE;
        currentStates[arraySize-arraySize/4-1][arraySize-arraySize/4+1] = ALIVE;
        currentStates[arraySize-arraySize/4+1][arraySize-arraySize/4+1] = ALIVE;
        currentStates[arraySize-arraySize/4+1][arraySize-arraySize/4-1] = ALIVE;

        //bottom left quadrant initialization
        currentStates[arraySize-arraySize/4][arraySize/4] = ALIVE;
        currentStates[arraySize-arraySize/4-1][arraySize/4-1] = ALIVE;
        currentStates[arraySize-arraySize/4-1][arraySize/4+1] = ALIVE;
        currentStates[arraySize-arraySize/4+1][arraySize/4+1] = ALIVE;
        currentStates[arraySize-arraySize/4+1][arraySize/4-1] = ALIVE;

        //top right quadrant initialization
        currentStates[arraySize/4][arraySize-arraySize/4] = ALIVE;
        currentStates[arraySize/4-1][arraySize-arraySize/4-1] = ALIVE;
        currentStates[arraySize/4-1][arraySize-arraySize/4+1] = ALIVE;
        currentStates[arraySize/4+1][arraySize-arraySize/4+1] = ALIVE;
        currentStates[arraySize/4+1][arraySize-arraySize/4-1] = ALIVE;
    }

    int gameOfLifeRule(int row, int column) {
        //Compute # of alive neighbors
        int neighborSum = currentStates[row-1][column-1] +
                            currentStates[row-1][column] +
                            currentStates[row-1][column+1] +
                            currentStates[row][column-1] +
                            currentStates[row][column+1] +
                            currentStates[row+1][column] +
                            currentStates[row+1][column-1] +
                            currentStates[row+1][column+1];
        if(currentStates[row][column] == ALIVE && (neighborSum == 2 || neighborSum == 3))
            return ALIVE;
        else if (currentStates[row][column] == DEAD && neighborSum == 3)
            return ALIVE;
        else
            return DEAD;
    }

    int flowerRule(int row, int column) {
        int sum = currentStates[row-1][column-1] +
                currentStates[row-1][column] +
                currentStates[row-1][column+1] +
                currentStates[row][column-1] +
                currentStates[row][column+1] +
                currentStates[row+1][column] +
                currentStates[row+1][column-1] +
                currentStates[row+1][column+1];
        if(sum == 5)
            return ALIVE;
        if(sum%2 == 0)
            return ALIVE;
        if(sum%2 != 0)
            return DEAD;
        else
            return DEAD;
    }

    /**
     * This method reduces flickering of the display
     * Don't change it.
     */
    public void update(Graphics g) {
        paint(g);
    }

    void delay(int delayTime) {
        // This block of code pauses the
        // program for 500ms (1/2 of a second)
        // It will be useful for animating your CA
        try {
            Thread.sleep(delayTime);
        } catch (Exception exc) {
        }
    }
}

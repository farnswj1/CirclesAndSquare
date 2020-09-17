/* Justin Farnsworth
 * March 20, 2016
 * Circles and Square
 * 
 * This program creates an animation which consists of 48 moving circles and an interactive square.
 * The circles move to their assigned directions. The square, found in the middle of the pad,
 * can be moved around with the arrows on the keyboard.
 * Chase down any circle that you choose!
 * 
 * Java Compile & Run Commands:
 * javac -cp +libs/doodlepad.jar CirclesAndSquare.java
 * java -cp .;+libs/doodlepad.jar CirclesAndSquare
 */

import doodlepad.*;
import java.util.ArrayList;
import java.util.Random;

public class CirclesAndSquare {
    // Instance variables
    private ArrayList<Oval> circles;
    
    private static Rectangle square;
    private Oval circle;
    private Oval s;
    
    public CirclesAndSquare() {
        // Creates the 600x600 pad
        Pad p = new Pad(600, 600);
        Rectangle background = new Rectangle(0, 0, 600, 600);
        background.setFillColor(135, 206, 250);
        p.setKeyPressedHandler( this::moveSquare );
        p.setTickHandler( this::step );
        
        Random rnd = new Random();

        // The array holds all the circles
        circles = new ArrayList<>();
        
        // Creates 48 circles
        for (int i = 0; i < 48; i++) {
            // The starting positions are randomized
            double x = 600.0*rnd.nextDouble();
            double y = 600.0*rnd.nextDouble();
            
            // Create the circle
            circle = new Oval(0, 0, 40, 40);
            circle.setCenter(x, y);

            // The color of the circles will also be random
            int r = rnd.nextInt(255);
            int g = rnd.nextInt(255);
            int b = rnd.nextInt(255);
            circle.setFillColor(r, g, b);

            // Append the circle to the array list
            circles.add(i, circle);
        }
        
        // Creates an interactive square in the middle of the pad
        square = new Rectangle(0, 0, 40, 40);
        square.setCenter(300, 300);
        square.setFillColor(0, 0, 0, 0);
        square.setStrokeWidth(4);

        // Sets timer for animation
        p.setTickRate(60);
        p.startTimer();
    }
    
    // Circles will animate by moving in an assigned direction
    public void step(Pad p, long t) {
        // Moves 6 circles left. The circles are automatically adjusted to stay in the pad
        for (int i = 0; i < 6; i++) {
            s = circles.get(i);
            s.move(-1, 0);
            
            // The circle is repositioned to the right of the screen if it goes too far left
            if (s.getX() <= -40) {
                s.move(640, 0);
            }
        }
        
        // Moves 6 circles right. The circles are automatically adjusted to stay in the pad
        for (int i = 6; i < 12; i++) {
            s = circles.get(i);
            s.move(1, 0);
            
            // The circle is repositioned to the left of the screen if it goes too far right
            if (s.getX() >= 600) {
                s.move(-640, 0);
            }
        }

        // Moves 6 circles up. The circles are automatically adjusted to stay in the pad
        for (int i = 12; i < 18; i++) {
            s = circles.get(i);
            s.move(0, -1);
            
            // The circle is repositioned to the bottom of the screen if it goes too far up
            if (s.getY() <= -40) {
                s.move(0, 640);
            }
        }

        // Moves 6 circles down. The circles are automatically adjusted to stay in the pad
        for (int i = 18; i < 24; i++) {
            s = circles.get(i);
            s.move(0, 1);
            
            // The circle is repositioned to the top of the screen if it goes too far down
            if (s.getY() >= 600) {
                s.move(0, -640);
            }
        }

        // Moves 6 circles up-left. The circles are automatically adjusted to stay in the pad
        for (int i = 24; i < 30; i++) {
            s = circles.get(i);
            s.move(-1, -1);
            
            // The circle is repositioned to the right of the screen if it goes too far left
            if (s.getX() <= -40) {
                s.move(640, 0);
            }
            // The circle is repositioned to the bottom of the screen if it goes too far up
            if (s.getY() <= -40) {
                s.move(0, 640);
            }
        }

        // Moves 6 circles up-right. The circles are automatically adjusted to stay in the pad
        for (int i = 30; i < 36; i++) {
            s = circles.get(i);
            s.move(1, -1);
            
            // The circle is repositioned to the left of the screen if it goes too far right
            if (s.getX() >= 600) {
                s.move(-640, 0);
            }
            // The circle is repositioned to the bottom of the screen if it goes too far up
            if (s.getY() <= -40) {
                s.move(0, 640);
            }
        }

        // Moves 6 circles down-left. The circles are automatically adjusted to stay in the pad
        for (int i = 36; i < 42; i++) {
            s = circles.get(i);
            s.move(-1, 1);
            
            // The circle is repositioned to the right of the screen if it goes too far left
            if (s.getX() <= -40) {
                s.move(640, 0);
            }
            // The circle is repositioned to the top of the screen if it goes too far down
            if (s.getY() >= 600) {
                s.move(0, -640);
            }
        }

        // Moves 6 circles down-right. The circles are automatically adjusted to stay in the pad
        for (int i = 42; i < 48; i++) {
            s = circles.get(i);
            s.move(1, 1);
            
            // The circle is repositioned to the left of the screen if it goes too far right
            if (s.getX() >= 600) {
                s.move(-640, 0);
            }
            // The circle is repositioned to the top of the screen if it goes too far down
            if (s.getY() >= 600) {
                s.move(0, -640);
            }
        }
    }
    
    public void moveSquare(Pad p, String keyText, String keyMods) {
        // Moves the square to the corresponding direction
        // The square will not be allowed to go off the screen
        switch (keyText) {
            case "Left":
                if (square.getX() >= 0) {
                    square.move(-2, 0);
                }
                break;
            case "Right":
                if (square.getX() <= 560) {
                    square.move(2, 0);
                }
                break;
            case "Up":
                if (square.getY() >= 0) {
                    square.move(0, -2);
                }
                break;
            case "Down":
                if (square.getY() <= 560) {
                    square.move(0, 2);
                }
                break;
           }
    }
    
    // Executes program
    public static void main(String[] args) {
        new CirclesAndSquare();
    }
}
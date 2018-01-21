package GamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;


public class GameMainClass implements ActionListener {

    public  static GameMainClass flappyBird;

    public final int WIDTH = 800, HEIGHT = 900;

    public Renderer renderer;

    public Rectangle Bird;

    public int ticks,yMotion;


    public ArrayList<Rectangle> columns;


    public GameMainClass() {

       //JFRAME SETUP
        JFrame jFrame = new JFrame();
        Renderer renderer = new Renderer();
        Timer timer = new Timer(20, this);
        // jframe settings

        jFrame.add(renderer);
        jFrame.setTitle("Flappy Bird Test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH,HEIGHT);
        jFrame.setVisible(true); // make window visible
        jFrame.setResizable(true); // resize
        jFrame.setVisible(true); // make window visible
// Player
        Bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
// Columns
        columns = new ArrayList<Rectangle>();
        timer.start();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        {
            int speed = 10;

            ticks++;

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                column.x -= speed;
            }

            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                if (column.x + column.width < 0) {
                    columns.remove(column);

                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }

            Bird.y += yMotion;
        }
    }



    public void addColumn(boolean start) { // column creation

        Random rand = new Random();

        int space = 300;
        int width = 100;
        int height = 50 + rand.nextInt(300);

        if (start) {
        columns.add(new Rectangle(WIDTH + width + columns.size()* 300,height -120,width,height));
        columns.add(new Rectangle(WIDTH + width + (columns.size() -1 )* 300,0 ,width,HEIGHT - height - space));
        }
        else {
            columns.add(new Rectangle(columns.get(columns.size() -1).x + 600, HEIGHT - height -120,width,height));
            columns.add(new Rectangle(columns.get(columns.size() -1).x,0,width,HEIGHT - height - space));
        }
    }

    public void paintColumn (Graphics g,Rectangle column) {
        // game columns
        g.setColor(Color.green.brighter());
        g.fillRect(column.x,column.y,column.width,column.height);
    }

    public void repaint(Graphics g) {

        g.setColor(Color.cyan); // background colour
        g.fillRect(0,0,WIDTH,HEIGHT); // background fill
        g.setColor(Color.red); // player colour
        g.fillRect(Bird.x,Bird.y,Bird.width,Bird.height); // player fill colour
// GROUND
        g.setColor(Color.orange);
        g.fillRect(0,HEIGHT -150,WIDTH,150);
// GRASS
        g.setColor(Color.green);
        g.fillRect(0,HEIGHT -150,WIDTH, 30);

        //COLOUMNS

        for (Rectangle column : columns)
        {
            paintColumn(g,column);

        }
    }



    public static void main(String[] args) {

        flappyBird = new GameMainClass();








    }



}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import saper.Box;
import saper.Coord;
import saper.Game;
import saper.Ranges;

public class JavaSaper extends JFrame {

    private Game game;

    private JPanel panel;
    private final int cols = 9;
    private  final int rows = 9;
    private  final  int BOMBS = 10;
    private final int image_size = 50;

    public static void main(String[] args) {

        new JavaSaper();
    }

    private JavaSaper (){

        game = new Game(cols, rows, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();

    }

    private void initPanel(){

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord: Ranges.getAllCoords())

                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x*image_size, coord.y * image_size, this);

            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

               int x = e.getX() / image_size;
               int y = e.getY() / image_size;
               Coord coord = new Coord(x,y);
               if (e.getButton() == MouseEvent.BUTTON1)
                   game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
               panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * image_size,
                Ranges.getSize().y * image_size));
        add(panel);
    }

    private void initFrame (){


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Сапер");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void setImages(){

        for (Box box: Box.values())
          box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage (String name){
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}

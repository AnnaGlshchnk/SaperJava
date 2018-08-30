import javax.swing.*;
import java.awt.*;
import saper.Box;
import saper.Coord;
import saper.Ranges;

public class JavaSaper extends JFrame {

    private JPanel panel;
    private final int cols = 9;
    private  final int rows = 9;
    private final int image_size = 50;

    public static void main(String[] args) {

        new JavaSaper();
    }

    private JavaSaper (){

        Ranges.setSize( new Coord(cols, rows));
        setImages();
        initPanel();
        initFrame();

    }

    private void initPanel(){

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord: Ranges.getAllCoords()) {

                    g.drawImage((Image) Box.values()[(coord.x + coord.y)% Box.values().length].image,
                            coord.x*image_size, coord.y * image_size, this);
                }
            }
        };

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * image_size,
                Ranges.getSize().y * image_size));
        add(panel);
    }

    private void initFrame (){

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Сапер");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
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

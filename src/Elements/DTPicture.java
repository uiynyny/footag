package Elements;

import model.ImageModel;
import view.starPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class DTPicture extends JPanel implements Observer {
    private ImageModel im;
    private Image image;
    private JComponent imagePane;
    //JLabel picLabel;
    private Dimension bound=new Dimension(240,160);
    private starPane filter = new starPane();

    public DTPicture(ImageModel im){
        this.im = im;
        image = new ImageIcon(im.getPath()).getImage();
        Dimension rescale = getScaledDimension(new Dimension(image.getWidth(this),image.getHeight(this)),bound);
        image=image.getScaledInstance((int)rescale.getWidth(),(int)rescale.getHeight(),Image.SCALE_SMOOTH);
        setPreferredSize(new Dimension(240,160));
        imagePane=new imagePane();
        controller();
        im.addObserver(this);
        im.updateView();
    }

    private void controller(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
        filter.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                filter.rate(e.getX());
                im.updateView();
            }
        });
        filter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                im.setRate(filter.preRate);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                filter.preRate= im.getRate();
                im.updateView();
            }
        });
        imagePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame newWindow = new JFrame(im.getName());
                newWindow.setLayout(new BorderLayout());
                newWindow.add(new JScrollPane(new JLabel(new ImageIcon(im.getPath()))),BorderLayout.CENTER);
                newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newWindow.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                });
                newWindow.setSize(800,600);
                newWindow.setVisible(true);
                im.updateView();
            }
        });
    }

    protected void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(),getHeight());
    }

    @Override
    public void update(Observable o, Object arg) {
        removeAll();
        setLayout(new BorderLayout());
        add(imagePane,BorderLayout.CENTER);
        Box vertBox=Box.createVerticalBox();
        Box horBox=Box.createHorizontalBox();
        vertBox.add(new JLabel(" "+im.getName()));
        vertBox.add(new JLabel(" "+im.getDate()));
        horBox.add(vertBox);
        horBox.add(filter);
        if(im.getMode()==Mode.Grid){
            add(horBox,BorderLayout.SOUTH);
        }else if(im.getMode()==Mode.List){
            add(horBox,BorderLayout.EAST);
        }
        validate();
        repaint();
    }

    private class imagePane extends JComponent{
        public imagePane(){
            setBackground(Color.white);
            setPreferredSize(new Dimension(240,160));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(image,5,5,this);
            validate();
        }
    }


    //rescale helper
    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }
}

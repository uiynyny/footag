package Elements;

import model.ImageModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DTPicture extends JPanel implements Observer {
    private ImageModel im;
    private Image image;
    private JComponent imagePane;
    private ratePane filter = new ratePane();

    public DTPicture(ImageModel im){
        this.im = im;
        image = new ImageIcon(im.getPath()).getImage().getScaledInstance(240,160,Image.SCALE_SMOOTH);
        setFocusable(true);
        setPreferredSize(new Dimension(240,200));
        imagePane=new imagePane();
        setLayout(new BorderLayout());
        add(imagePane,BorderLayout.CENTER);
        Box vertBox=Box.createVerticalBox();
        Box horBox=Box.createHorizontalBox();
        vertBox.add(new JLabel(" "+im.getName()));
        vertBox.add(new JLabel(" "+im.getDate()));
        horBox.add(vertBox);
        horBox.add(filter);
        add(horBox,BorderLayout.SOUTH);
        controller();
        im.setPic(this);
        im.addObserver(this);
    }

    private void controller(){
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
               im.updateView();
            }

            @Override
            public void focusLost(FocusEvent e) {
                im.updateView();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    protected void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(),getHeight());
        if(isFocusOwner()){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.BLACK);
        }
        g.drawRect(0,0,getWidth(),getHeight());
    }

    @Override
    public void update(Observable o, Object arg) {
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
        }
    }

    private class ratePane extends JPanel{
        private int preRate=0;
        private ArrayList<Shape> stars=new ArrayList<>();

        public ratePane(){
            for(int i=1;i<=5;i++){
                stars.add(new Star(14*i,0));
            }
            registerController();
            setBackground(Color.red);
            setPreferredSize(new Dimension(80,15));
            setMaximumSize(new Dimension(80,15));
        }

        private void registerController(){
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    //System.err.println("moved");
                    if(e.getX()>=7 && e.getX()<=21){
                        preRate = 1;
                    }else if(e.getX()>21 && e.getX()<=35){
                        preRate = 2;
                    }else if(e.getX()>35 && e.getX()<=49){
                        preRate = 3;
                    }else if(e.getX()>49 && e.getX()<=63){
                        preRate = 4;
                    }else if(e.getX()>63 && e.getX()<=77){
                        preRate = 5;
                    }
                    im.updateView();
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    im.setRate(preRate);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    preRate= im.getRate();
                    im.updateView();
                }
            });
        }
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
//            g2d.setColor(Color.WHITE);
//            g2d.fillRect(0,0,getWidth(),getHeight());
            g2d.setColor(Color.BLACK);
            for(int i=0;i<5;i++){
                g2d.draw(stars.get(i));
            }
            for(int i=0;i<preRate;i++){
                g2d.setColor(Color.BLACK);
                g2d.fill(stars.get(i));
            }
        }
    }
}

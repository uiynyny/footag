package Elements;

import model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class DTPicture extends JPanel implements Observer {
    private ImageModel im;
    private Image image;
    private JComponent imagePane;

    public DTPicture(ImageModel im){
        this.im = im;
        image = new ImageIcon(im.getPath()).getImage().getScaledInstance(240,160,Image.SCALE_SMOOTH);
        setFocusable(true);
        setPreferredSize(new Dimension(240,200));
        imagePane=new imagePane();
        setLayout(new BorderLayout());
        add(imagePane,BorderLayout.CENTER);
        Box vertBox=Box.createVerticalBox();
        vertBox.add(new JLabel(im.getName()));
        vertBox.add(new JLabel(im.getDate()));
        add(vertBox,BorderLayout.SOUTH);
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
        g.drawRect(0,0,image.getWidth(this),image.getHeight(this));
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
            g.drawImage(image,0,0,this);
        }
    }
}

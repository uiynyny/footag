package Elements;

import model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;
import java.util.Observer;

public class DTPicture extends JComponent implements Observer {
    private ImageModel im;
    private Image image;

    public DTPicture(ImageModel im){
        this.im = im;
        image = new ImageIcon(im.getPath()).getImage();

        setFocusable(true);
        setPreferredSize(new Dimension(80,60));

        controller();
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
    }

    protected void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(this),image.getHeight(this));
        g.drawImage(image,0,0,this);

        if(isFocusOwner()){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.BLACK);
        }
        g.drawRect(0,0,image.getWidth(this),image.getHeight(this));
        g.dispose();
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

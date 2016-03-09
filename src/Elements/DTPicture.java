package Elements;

import model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by temp user on 09/03/16.
 */
public class DTPicture extends JComponent implements Observer {
    private ImageModel im;
    private Image image;

    public DTPicture(ImageModel im){
        this.im = im;
        image = new ImageIcon(im.getPath()).getImage();
        layoutComponent();
        im.addObserver(this);
    }

    private void layoutComponent(){

    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

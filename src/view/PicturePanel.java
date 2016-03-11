package view;


import Elements.DTPicture;
import Elements.Mode;
import model.ImageCollectionModel;
import model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;

public class PicturePanel extends JPanel implements Observer {
    private ImageCollectionModel icm;
    public PicturePanel(ImageCollectionModel icm){
        this.icm=icm;
        layoutComponent();
        controller();
        icm.addObserver(this);
        icm.updateView();
    }

    private void layoutComponent(){
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        validate();
    }

    private void controller(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //System.err.println(getWidth() + " " + getHeight());
                icm.updateView();
            }
        });
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,0,getWidth(),getHeight());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(icm.getMode()==Mode.Grid) {
            if (getWidth() <= 780) {
                setLayout(new GridLayout(0, 2, 10, 10));
            } else if (getWidth() > 780 && getWidth() <= 1030) {
                setLayout(new GridLayout(0, 3, 10, 10));
            } else if (getWidth() > 1030 && getWidth() <= 1280) {
                setLayout(new GridLayout(0, 4, 10, 10));
            } else {
                setLayout(new GridLayout(0, 5, 10, 10));
            }
        }else {
            setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        }

        removeAll();
        for(ImageModel im:icm.listModel){
            if(im.getRate()>=icm.getRateFilter()){
                this.add(im.getPic());
            }
        }
        validate();
    }
}

package view;

import Elements.DTPicture;
import model.ImageCollectionModel;
import model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PicturePanel extends JPanel implements Observer {
    private ArrayList<DTPicture> pic = new ArrayList<>();
    private ImageCollectionModel icm;
    public PicturePanel(ImageCollectionModel icm){
        this.icm=icm;

        layoutComponent();
    }

    private void layoutComponent(){
        setLayout(new GridLayout(3,0));
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(630,520));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawLine(0,0,getWidth(),getHeight());
    }

    @Override
    public void update(Observable o, Object arg) {
        for(ImageModel im: icm.listModel){

        }
    }
}

package view;

import Elements.DTPicture;
import model.ImageCollectionModel;
import model.ImageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.util.Observable;
import java.util.Observer;

public class PicturePanel extends JPanel implements Observer {
    //private ArrayList<DTPicture> pic = new ArrayList<>();
    private ImageCollectionModel icm;
    public PicturePanel(ImageCollectionModel icm){
        this.icm=icm;
        layoutComponent();
        icm.addObserver(this);
        controller();
        icm.updateView();
    }

    private void layoutComponent(){
        setLayout(new GridLayout(0,3));
        setBackground(Color.GRAY);
        //setPreferredSize(new Dimension(630,520));

        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    private void controller(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.err.println(getWidth()+" "+getHeight());
                if(getWidth()<=760){
                    setLayout(new GridLayout(0,2));
                }else if(getWidth()>760 && getWidth()<=1000){
                    setLayout(new GridLayout(0,3));
                }else if(getWidth()>1000 && getWidth()<=1240){
                    setLayout(new GridLayout(0,4));
                }else{
                    setLayout(new GridLayout(0,5));
                }
                validate();
            }
        });
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,getWidth(),getHeight());
    }

    @Override
    public void update(Observable o, Object arg) {
        icm.listpic.forEach(this::add);
        validate();
    }
}

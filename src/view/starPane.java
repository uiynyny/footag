package view;

import Elements.Star;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class starPane extends JPanel {
public ArrayList<Shape> stars=new ArrayList<>();
public int preRate=0;


    public starPane(){
        for(int i=1;i<=5;i++){
            stars.add(new Star(14*i,0));
        }
        setBackground(Color.red);
        setPreferredSize(new Dimension(80,15));
        setMaximumSize(new Dimension(80,15));
    }

    public void rate(int x){
        if(x<7) {
            preRate = 0;
        }
        else if(x>=7 && x<=21){
            preRate = 1;
        }else if(x>21 && x<=35){
            preRate = 2;
        }else if(x>35 && x<=49){
            preRate = 3;
        }else if(x>49 && x<=63){
            preRate = 4;
        }else if(x>63 && x<=77){
            preRate = 5;
        }
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

import model.ImageCollectionModel;
import view.ToolView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    private static void createAndShow(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.err.println("look and feel not set.");
        }
        JFrame frame= new JFrame("Footag!");
        frame.setLayout(new BorderLayout());
         ImageCollectionModel icm= new ImageCollectionModel();

        ToolView toolView = new ToolView(icm);
        frame.setLayout(new BorderLayout());
        frame.add(toolView,BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShow();
            }
        });
    }
}

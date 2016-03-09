import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;

public class FooTag {

    private static void MainView(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.err.println("look and feel not set.");
        }
        JFrame frame= new JFrame("Footag!");
        frame.setLayout(new BorderLayout());
        ImageCollectionModel icm= new ImageCollectionModel();

        ToolView toolView = new ToolView(icm);
        PicturePanel picturePanel = new PicturePanel();
        JScrollPane jScrollPane = new JScrollPane(picturePanel);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.getVerticalScrollBar().addAdjustmentListener(e->{
            frame.repaint();
        });

        frame.setLayout(new BorderLayout());
        frame.add(toolView,BorderLayout.NORTH);
        frame.add(jScrollPane,BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(FooTag::MainView);
    }
}

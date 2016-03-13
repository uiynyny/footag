import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

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
        PicturePanel picturePanel = new PicturePanel(icm);
        JScrollPane jScrollPane = new JScrollPane(picturePanel);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.getVerticalScrollBar().addAdjustmentListener(e->{
            frame.repaint();
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(icm.listModel.size()!=0){
                    File file= new File("saved.list");
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(out);
                        oos.writeObject(icm.listModel);
                        oos.close();
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {
                if(new File("saved.list").exists()){
                    System.err.println("file exist");
                    try{
                        FileInputStream in = new FileInputStream(new File("saved.list"));
                        ObjectInputStream ois = new ObjectInputStream(in);
                        //ImageCollectionModel icm =new ImageCollectionModel();
                        icm.listModel= (ArrayList<ImageModel>) ois.readObject();
                        in.close();
                        ois.close();
                        icm.loadupdate();
                    }catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    System.err.println("file not exist");
                }
            }
        });
        frame.setLayout(new BorderLayout());
        frame.add(toolView,BorderLayout.NORTH);
        frame.add(jScrollPane,BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(630,520));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(FooTag::MainView);
    }
}

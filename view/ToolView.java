package view;

import model.ImageCollectionModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by temp user on 06/03/16.
 */
public class ToolView extends JPanel  {
    private JButton open = new JButton(new ImageIcon("icon/upload.png"));
    private JButton grid = new JButton(new ImageIcon("icon/grid.png"));
    private JButton list = new JButton(new ImageIcon("icon/list.png"));
    private JPanel filter = new JPanel();
    private ImageCollectionModel icm;
    public ToolView(ImageCollectionModel icm){
        super();
        this.icm=icm;
        layoutView();
        controller();
    }

    private void layoutView(){
        SpringLayout layout = new SpringLayout();
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(open);
        add(Box.createHorizontalStrut(10));
        add(grid);
        add(Box.createHorizontalStrut(10));
        add(list);
        add(Box.createHorizontalGlue());
        add(filter);
        filter.setBackground(Color.red);
        filter.setPreferredSize(new Dimension(50,30));
//        layout.putConstraint(SpringLayout.EAST,this,5,SpringLayout.EAST,filter);
//        layout.putConstraint(SpringLayout.WEST,this,5,SpringLayout.WEST,open);
//        layout.putConstraint(SpringLayout.EAST,open,5,SpringLayout.WEST,grid);
//        layout.putConstraint(SpringLayout.EAST,grid,5,SpringLayout.WEST,list);
//        layout.putConstraint(SpringLayout.NORTH,this,5,SpringLayout.NORTH,open);
//        layout.putConstraint(SpringLayout.NORTH,this,5,SpringLayout.NORTH,grid);
//        layout.putConstraint(SpringLayout.NORTH,this,5,SpringLayout.NORTH,list);
//        layout.putConstraint(SpringLayout.NORTH,this,5,SpringLayout.NORTH,filter);
//        layout.putConstraint(SpringLayout.SOUTH,this,5,SpringLayout.SOUTH,filter);
//        layout.putConstraint(SpringLayout.SOUTH,this,5,SpringLayout.SOUTH,list);
//        layout.putConstraint(SpringLayout.SOUTH,this,5,SpringLayout.SOUTH,grid);
//        layout.putConstraint(SpringLayout.SOUTH,this,5,SpringLayout.SOUTH,open);

    }

    public void paintComponent(Graphics g){

    }

    private void controller(){
        open.addActionListener(e->{
            try{
                JFileChooser fc= new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Picture","jpg","jpeg","gif","png","bmp","tif","tiff"));
                fc.setAcceptAllFileFilterUsed(false);
                fc.setCurrentDirectory(new File("user/Desktop"));
                int ret = fc.showOpenDialog(fc);
                if(ret== JFileChooser.APPROVE_OPTION){
                    String ext =fc.getFileFilter().getDescription();
                    File file = fc.getSelectedFile();
                    String name = file.getName();
                }
            }catch(IOException ie){
                ie.printStackTrace();
            }
        });
    }
}

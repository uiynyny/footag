package view;

import Elements.*;
import model.ImageCollectionModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;


/**
 * Created by temp user on 06/03/16.
 */
public class ToolView extends JPanel  {
    private JButton open = new JButton(new ImageIcon("icon/upload.png"));
    private JButton grid = new JButton(new ImageIcon("icon/grid.png"));
    private JButton list = new JButton(new ImageIcon("icon/list.png"));
    private FilterPane filter = new FilterPane();
    private ImageCollectionModel icm;
    private Shape[] star;

    public ToolView(ImageCollectionModel icm){
        super();
        this.icm=icm;
        layoutView();
        controller();
    }

    private void layoutView(){
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(open);
        add(Box.createHorizontalStrut(10));
        add(grid);
        add(Box.createHorizontalStrut(10));
        add(list);
        add(Box.createHorizontalGlue());
        add(filter);

    }

    private class FilterPane extends JPanel{
        public FilterPane(){
            for(int i=0;i<5;i++){
                star[i]=new Star(14*i/2,3);
            }
            setBackground(Color.red);
            setPreferredSize(new Dimension(70,20));
        }

        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.BLACK);
            for(int i=0;i<5;i++){
                g2d.draw(star[i]);
            }
        }
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
                    File file = fc.getSelectedFile();
                    String name = file.getName();
                    String path = file.getAbsolutePath();
                    BasicFileAttributes atr = Files.readAttributes(Paths.get(path),BasicFileAttributes.class);
                    FileTime time= atr.creationTime();
                }
            }catch(IOException ie){
                ie.printStackTrace();
            }
        });
    }
}

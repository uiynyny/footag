package view;

import Elements.*;
import model.ImageCollectionModel;
import model.ImageModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

public class ToolView extends JPanel implements Observer {

    Image upload = new ImageIcon("icon/upload.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
    Image g = new ImageIcon("icon/grid.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
    Image l = new ImageIcon("icon/list.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
    private JButton open = new JButton(new ImageIcon(upload));
    private JButton grid = new JButton(new ImageIcon(g));
    private JButton list = new JButton(new ImageIcon(l));
    private JButton clear = new JButton("Clear");
    private ImageCollectionModel icm;
    private JLabel label = new JLabel("Filter by:");
    private starPane filter = new starPane();

    public ToolView(ImageCollectionModel icm){
        super();
        this.icm=icm;
        layoutView();
        controller();
        icm.addObserver(this);
        icm.updateView();
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
        add(label);
        add(Box.createHorizontalStrut(5));
        add(filter);
        add(clear);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }



    private void controller(){
        filter.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                filter.rate(e.getX());
                icm.updateView();
            }
        });

        filter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                icm.setRateFilter(filter.preRate);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                filter.preRate= icm.getRateFilter();
                icm.updateView();
            }
        });

        clear.addActionListener(e->{
            filter.preRate=0;
            icm.setRateFilter(0);
        });
        open.addActionListener(e->{
            try{
                JFileChooser fc= new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Picture","jpg","jpeg","gif","png","bmp","tif","tiff"));
                fc.setAcceptAllFileFilterUsed(false);
                fc.setMultiSelectionEnabled(true);
                int ret = fc.showOpenDialog(fc);
                if(ret== JFileChooser.APPROVE_OPTION){
                    File[] file = fc.getSelectedFiles();
                    for (File aFile : file) {
                        String name = aFile.getName();
                        String path = aFile.getAbsolutePath();
                        //BasicFileAttributes atr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
                        FileTime time = Files.readAttributes(Paths.get(path), BasicFileAttributes.class).creationTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        String date = sdf.format(time.toMillis());
//                        System.err.println(name);
//                        System.err.println(date);
//                        System.err.println(path);
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        try (InputStream is = Files.newInputStream(Paths.get(path));
                             DigestInputStream dis = new DigestInputStream(is, md))
                        {
                        }
                        byte[] digest = md.digest();
                        System.err.println(digest);
                        ImageModel im = new ImageModel(name, path, date);
                        icm.addModel(im);
                        setSize(getWidth()+1,getHeight());
                        setSize(getWidth()-1,getHeight());
                    }
                }
            }catch(IOException ie){
                ie.printStackTrace();
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        });

        grid.addActionListener(e->{
            icm.setMode(Mode.Grid);
        });
        list.addActionListener(e->{
            icm.setMode(Mode.List);
        });
    }
}

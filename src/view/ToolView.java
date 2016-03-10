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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private ArrayList<Shape> stars=new ArrayList<>();
    private JLabel label = new JLabel("Filter by:");
    private int preRate = 0;

    public ToolView(ImageCollectionModel icm){
        super();
        this.icm=icm;
        layoutView();
        controller();
        icm.addObserver(this);
        icm.updateView();
    }

    private FilterPane filter = new FilterPane();

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

    private class FilterPane extends JPanel{

        public FilterPane(){
            for(int i=1;i<=5;i++){
                stars.add(new Star(14*i,0));
            }
            registerController();
            setBackground(Color.red);
            setPreferredSize(new Dimension(80,15));
            setMaximumSize(new Dimension(80,15));
        }

        private void registerController(){
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    //System.err.println("moved");
                    if(e.getX()>=7 && e.getX()<=21){
                        preRate = 1;
                    }else if(e.getX()>21 && e.getX()<=35){
                        preRate = 2;
                    }else if(e.getX()>35 && e.getX()<=49){
                        preRate = 3;
                    }else if(e.getX()>49 && e.getX()<=63){
                        preRate = 4;
                    }else if(e.getX()>63 && e.getX()<=77){
                        preRate = 5;
                    }
                    icm.updateView();
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    icm.setRateFilter(preRate);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    preRate= icm.getRateFilter();
                    icm.updateView();
                }
            });
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

    private void controller(){
        clear.addActionListener(e->{
            preRate=0;
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
                        ImageModel im = new ImageModel(name, path, date);
                        icm.addModel(im);
                    }
                }
            }catch(IOException ie){
                ie.printStackTrace();
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

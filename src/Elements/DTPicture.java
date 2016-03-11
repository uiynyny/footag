package Elements;

import model.ImageModel;
import view.starPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class DTPicture extends JPanel implements Observer {
    private ImageModel im;
    private Image image;
    private JComponent imagePane;
    private starPane filter = new starPane();

    public DTPicture(ImageModel im){
        this.im = im;
        image = new ImageIcon(im.getPath()).getImage().getScaledInstance(240,160,Image.SCALE_SMOOTH);
        setPreferredSize(new Dimension(240,200));
        imagePane=new imagePane();
        controller();
        im.setPic(this);
        im.addObserver(this);
        im.updateView();
    }

    private void controller(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
        filter.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                filter.rate(e.getX());
                im.updateView();
            }
        });
        filter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                im.setRate(filter.preRate);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                filter.preRate= im.getRate();
                im.updateView();
            }
        });
        imagePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame newWindow = new JFrame(im.getName());
                newWindow.setLayout(new BorderLayout());
                newWindow.add(new JScrollPane(new JLabel(new ImageIcon(im.getPath()))),BorderLayout.CENTER);
                newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newWindow.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                });
                newWindow.pack();
                newWindow.setVisible(true);
                im.updateView();
            }
        });
    }

    protected void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(),getHeight());

    }

    @Override
    public void update(Observable o, Object arg) {
        removeAll();
        setLayout(new BorderLayout());
        add(imagePane,BorderLayout.CENTER);
        Box vertBox=Box.createVerticalBox();
        Box horBox=Box.createHorizontalBox();
        vertBox.add(new JLabel(" "+im.getName()));
        vertBox.add(new JLabel(" "+im.getDate()));
        horBox.add(vertBox);
        horBox.add(filter);
        if(im.getMode()==Mode.Grid){
            add(horBox,BorderLayout.SOUTH);
        }else if(im.getMode()==Mode.List){
            add(horBox,BorderLayout.EAST);
        }
        validate();
        repaint();
    }

    private class imagePane extends JComponent{
        public imagePane(){
            setBackground(Color.white);
            setPreferredSize(new Dimension(240,160));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(image,5,5,this);
            validate();
        }
    }

}

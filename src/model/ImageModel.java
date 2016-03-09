package model;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Observable;


public class ImageModel extends Observable implements Serializable{
    //private Image image;
    private String name;
    private int rate;
    private String path;
    private String date;

    public int getRate(){
        return rate;
    }
    public void setRate(int r){
        rate=r;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }

    public ImageModel(String name, String filepath, String date) {
        this.name=name;
        //this.image= new ImageIcon(path).getImage();
        this.path = filepath;
        this.date = date;
        this.rate = 0;
    }

    public void updateView(){
        setChanged();
        notifyObservers();
    }

}

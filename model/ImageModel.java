package model;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * Created by temp user on 06/03/16.
 */
public class ImageModel extends Observable implements Serializable{
    private Image image;
    private int rate;
    private String path;
    private Date date;

    public int getRate(){
        return rate;
    }
    public void setRate(int r){
        rate=r;
        setChanged();
        notifyObservers();
    }

    public Date getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }

    public ImageModel(Image img, String filepath, Date date) {
        this.image=img;
        this.path = filepath;
        this.date = date;
        this.rate = 0;
    }

    public void updateView(){
        setChanged();
        notifyObservers();
    }


}

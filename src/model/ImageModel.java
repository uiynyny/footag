package model;

import Elements.DTPicture;
import Elements.Mode;
import java.io.Serializable;
import java.util.Observable;


public class ImageModel extends Observable implements Serializable{
    private String name;
    private int rate;
    private String path;
    private String date;
    private Mode mode=Mode.Grid;

    public void setMode(Mode m){
        mode=m;
        setChanged();
        notifyObservers();
    }

    public Mode getMode() {
        return mode;
    }

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
        this.path = filepath;
        this.date = date;
        this.rate = 0;
    }

    public void updateView(){
        setChanged();
        notifyObservers();
    }
}

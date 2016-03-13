package model;

import Elements.DTPicture;
import Elements.Mode;
import java.io.Serializable;
import java.util.Observable;


public class ImageModel extends Observable implements Serializable{
    private transient DTPicture pic;
    private transient ImageCollectionModel icm;
    private String name;
    private int rate;
    private String path;
    private String date;
    private transient Mode mode=Mode.Grid;

    public void setMode(Mode m){
        mode=m;
        setChanged();
        notifyObservers();
    }

    public void setIcm(ImageCollectionModel icm){
        this.icm=icm;
    }


    public Mode getMode() {
        return mode;
    }

    public int getRate(){
        return rate;
    }

    public void setRate(int r){
        rate=r;
        updateView();
    }

    public DTPicture getPic() {
        return pic;
    }

    public void setPic(){
        pic=new DTPicture(this);
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
        setPic();
    }

    public void updateView(){
        setChanged();
        notifyObservers();
    }
    public void supUpdate(){
        icm.updateView();
    }
}

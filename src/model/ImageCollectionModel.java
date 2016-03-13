package model;

import Elements.DTPicture;
import Elements.Mode;

import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable {
    public ArrayList<ImageModel> listModel;
    //public ArrayList<DTPicture> listpic;
    private int rateFilter;
    private Mode m;

    public int getRateFilter() {
        return rateFilter;
    }

    public void setRateFilter(int r){
        rateFilter=r;
        setChanged();
        notifyObservers();
    }

    public void setMode(Mode m){
        this.m=m;
        for(ImageModel im: listModel){
            im.setMode(m);
        }
        setChanged();
        notifyObservers();
    }

    public Mode getMode(){
        return m;
    }

    public ImageCollectionModel(){
        listModel=new ArrayList<>();
        m=Mode.Grid;
    }

    public void addModel(ImageModel im){
        listModel.add(im);
        //listpic.add(new DTPicture(im));
        im.updateView();
        setChanged();
        notifyObservers();
    }

    public void removeModel(ImageModel im) {
        listModel.remove(im);
        setChanged();
        notifyObservers();
    }

    public void updateView(){
        setChanged();
        notifyObservers();
    }

    public void loadupdate(){
        for(ImageModel im: listModel){
            im.setPic();
            im.getPic().setpreRate(im.getRate());
        }
        setChanged();
        notifyObservers();
    }
}

package model;

import Elements.DTPicture;

import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable {
    public ArrayList<ImageModel> listModel;
    public ArrayList<DTPicture> listpic;
    private int rateFilter;

    public int getRateFilter() {
        return rateFilter;
    }

    public void setRateFilter(int r){
        rateFilter=r;
        setChanged();
        notifyObservers();
    }

    public ImageCollectionModel(){
        listModel=new ArrayList<>();
        listpic=new ArrayList<>();
    }

    public void addModel(ImageModel im){
        listModel.add(im);
        listpic.add(new DTPicture(im));
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
}

package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by temp user on 06/03/16.
 */
public class ImageCollectionModel extends Observable {
    public ArrayList<ImageModel> listModel;
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
    }

    public void addModel(ImageModel im){
        listModel.add(im);
        setChanged();
        notifyObservers();
    }

    public void removeModel(ImageModel im) {
        listModel.remove(im);
        setChanged();
        notifyObservers();
    }

}

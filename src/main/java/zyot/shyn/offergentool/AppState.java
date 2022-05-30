package zyot.shyn.offergentool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import zyot.shyn.offergentool.offer.OfferObject;

import java.util.ArrayList;

public class AppState {
    private static final Object lock = new Object();
    private static AppState instance = null;

    private Stage secondWindow;
    private OfferObject selectedOffer;

    private ObservableList<OfferObject> offerList;

    private AppState() {
        secondWindow = new Stage();
        secondWindow.setResizable(false);
        offerList = FXCollections.observableArrayList();
    }

    public static AppState getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new AppState();
            }
        }
        return instance;
    }

    public ObservableList<OfferObject> getOfferList() {
        return offerList;
    }

    public void setOfferList(ArrayList<OfferObject> offers) {
        if (offers == null || offers.size() == 0)
            return;
        offerList.clear();
        offerList.addAll(offers);
    }

    public void addNewOffer(OfferObject offerObject) {
        offerList.add(offerObject);
    }

    public void removeOffer(OfferObject offerObject) {
        offerList.remove(offerObject);
    }

    public Stage getSecondWindow() {
        return secondWindow;
    }

    public OfferObject getSelectedOffer() {
        return selectedOffer;
    }

    public void setSelectedOffer(OfferObject selectedOffer) {
        this.selectedOffer = selectedOffer;
    }

    public void updateOfferInfo(OfferObject offerObject) {
        for (OfferObject offer : offerList) {
            if (offer.id == offerObject.id) {
                int idx = offerList.indexOf(offer);
                offerList.remove(idx);
                offerList.add(idx, offerObject);
                break;
            }
        }
    }

    public void closeSecondWindow() {
        secondWindow.close();
    }
}

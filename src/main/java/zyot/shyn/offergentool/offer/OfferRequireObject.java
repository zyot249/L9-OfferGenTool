package zyot.shyn.offergentool.offer;

public class OfferRequireObject {
    public String purchasedRuby = "";       // min_max (-1 : infinity)
    public String gameAge = "";             // min_max (-1 : infinity)

    public OfferRequireObject() {
        super();
    }

    public void init() {
        purchasedRuby = "0_-1";
        gameAge = "0_-1";
    }
}

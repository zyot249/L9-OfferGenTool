package zyot.shyn.offergentool.offer;

public class OfferRequireObject {
    public String purchasedRuby = "";       // min_max (-1 : infinity)
    public String gameAge = "";             // min_max (-1 : infinity)
    public int lastPayDays = -1;            // day difference from now (-1 : infinity)
    public String rubyInLastPay = "";       // min_max (-1 : infinity)

    public OfferRequireObject() {
        super();
    }
}

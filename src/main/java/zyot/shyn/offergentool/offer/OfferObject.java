package zyot.shyn.offergentool.offer;

public class OfferObject {
    public int id = -1;
    public boolean active = false;
    public int priority = 0;       // offer with bigger priority will be shown first
    public OfferOpenType openType = OfferOpenType.OPEN_AT_LOGIN;

    public OfferInfoObject info = new OfferInfoObject();
    public OfferRequireObject requirement = new OfferRequireObject();

    public OfferObject() {
        super();
    }

    public OfferObject(int id) {
        super();
        this.id = id;
    }

    public void init() {
        priority = 1;
        info.init(id);
        requirement.init();
    }

    @Override
    public String toString() {
        return info.displayName;
    }
}

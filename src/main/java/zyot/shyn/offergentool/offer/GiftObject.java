package zyot.shyn.offergentool.offer;


public class GiftObject {
    public String itemId;
    public int num;
    public int rate;

    public GiftObject() {
        super();
        itemId = "";
        this.num = 0;
        this.rate = 0;
    }

    public GiftObject(String item, int num, int rate) {
        super();
        itemId = item;
        this.num = num;
        this.rate = rate;
    }

    public GiftObject(String item, int num) {
        super();
        itemId = item;
        this.num = num;
        this.rate = 0;
    }

    public GiftObject clone() {
        GiftObject result = new GiftObject();
        result.itemId = this.itemId;
        result.num = this.num;
        result.rate = this.rate;

        return result;
    }
}
package zyot.shyn.offergentool.offer;


import java.util.ArrayList;
import java.util.HashMap;

public class OfferInfoObject {
    public String name = "";
    public String displayName = "";
    public String content = "";
    public String endow = "";
    public int guiStyle = 1;

    public int startTime = 0;           // format: yyyyMMdd
    public int endTime = 0;             // format: yyyyMMdd
    public int showHour = 0;
    public int buyCountDownHour = 0;
    public int notBuyCountDownHour = 0;

    public int limitPerUser = 0;        // -1 : no limit
    public String location = "";

    public HashMap<String, Integer> priceByTypes = new HashMap<>();     // (currency_payType -> realGross)
    public ArrayList<GiftObject> giftList = new ArrayList<>();

    public OfferInfoObject() {
        super();
    }

    public void init(int id) {
        displayName = "Offer " + id;
        priceByTypes.put("PHP_sms", 0);
        priceByTypes.put("PHP_iap", 0);
        priceByTypes.put("PHP_ewallet", 0);
        priceByTypes.put("USD_iap", 0);
    }
}
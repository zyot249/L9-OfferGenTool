package zyot.shyn.offergentool.offer;

public enum OfferOpenType {
    OPEN_AT_LOGIN,
    OPEN_WHEN_GOLD_LOW;

    public static OfferOpenType getTypeOrDefault(String value, OfferOpenType defaultResult) {
        if (defaultResult == null)
            return null;

        if (value == null || value.equals(""))
            return defaultResult;

        if (value.equals(OPEN_AT_LOGIN.name()))
            return OPEN_AT_LOGIN;
        else if (value.equals(OPEN_WHEN_GOLD_LOW.name()))
            return OPEN_WHEN_GOLD_LOW;
        else return defaultResult;
    }
}

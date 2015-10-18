/**
 * Created by Hongda Zeng on 10/17/2015!
 */
public class ItemLoc {
    double upc;
    String loc;
    double ourPrice;

    ItemLoc(double theUpc, String theLoc) {
        upc = theUpc;
        loc = theLoc;
    }

    ItemLoc(double theUpc, String theLoc, double thePrice) {
        upc = theUpc;
        loc = theLoc;
        ourPrice = thePrice;
    }

    public double getUPC() {
        return upc;
    }

    public String getLoc() {
        return loc;
    }
}

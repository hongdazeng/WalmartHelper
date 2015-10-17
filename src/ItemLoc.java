/**
 * Created by Hongda Zeng on 10/17/2015!
 */
public class ItemLoc {
    double upc;
    String loc;

    ItemLoc(double theUpc, String theLoc) {
        upc = theUpc;
        loc = theLoc;
    }

    public double getUPC() {
        return upc;
    }

    public String getLoc() {
        return loc;
    }
}

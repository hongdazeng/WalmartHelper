import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hongda Zeng on 10/17/2015!
 */
public class APIcaller {

    String productName;
    String newCall;
    URL url;

    String response = "";
    double upc;

    APIcaller(String productName) {
        this.productName = productName;
        this.newCall = buildCall();
    }

    //public static void main(String[] args) throws IOException {
    //    APIcaller newCall = new APIcaller("ipod");
    //    System.out.println(newCall.getUPC());
    //}

    public String buildCall() {
        return ("http://api.walmartlabs.com/v1/search?" +
                "query=" + productName + "&format=json&apiKey=34ktn2ap7hfenq9xv9ry8gv2");
    }

    public String doCall() throws IOException {
        try {
            url = new URL(newCall);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String temp;

        while ((temp = in.readLine()) != null) {
            response += temp;
        }

        return response;
    }

    public double getUPC() throws IOException {

        String callResponse = doCall();

        int start = callResponse.indexOf("upc");
        start += 6; //offset to start of upc
        String upcStr = callResponse.substring(start, start + 12);
        upc = Double.parseDouble(upcStr);

        return upc;
    }


}

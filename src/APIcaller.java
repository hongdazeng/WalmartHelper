import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * Created by Hongda Zeng on 10/17/2015!
 */
public class APIcaller {

    static String dealCallResponse;
    String productName;
    String newCall;
    URL url;
    String response = "";
    String dealResponse = "";
    double upc;
    String doCallResponse;

    String APIkey = "fill me"; // TODO: Fill in your own API key to get data

    APIcaller(String productName) {
        this.productName = productName;
        this.newCall = buildCall();
        try {
            doCall();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public static void main(String[] args) throws IOException {
    //    APIcaller newCall = new APIcaller("ipod");
    //    System.out.println(newCall.getUPC());
    //}

    public String buildCall() {
        return ("http://api.walmartlabs.com/v1/search?" +
                "query=" + productName + "&format=json&apiKey=" + APIkey);
    }

    public void doCall() throws IOException {
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

        doCallResponse = response;
    }

    public double getUPC() {

        String callResponse = doCallResponse;

        int start = callResponse.indexOf("upc");
        start += 6; //offset to start of upc
        String upcStr = callResponse.substring(start, start + 12);
        upc = Double.parseDouble(upcStr);

        return upc;
    }

    public String getName() {
        String str = doCallResponse;
        int indName = str.indexOf("name");
        StringTokenizer par = new StringTokenizer(str.substring(indName), ",");
        //System.out.println("The Name: "+par.nextToken().split(":")[1].replace("\"", ""));
        return par.nextToken().split(":")[1].replace("\"", "");
    }

    public double getPrice() {
        String str = doCallResponse;
        int indPrice = str.indexOf("salePrice");
        //next is the sales price
        StringTokenizer par = new StringTokenizer(str.substring(indPrice), ",");
        //System.out.println("The Price: $"+par.nextToken().split(":")[1].replace("\"", ""));
        return Double.parseDouble(par.nextToken().split(":")[1].replace("\"", ""));

    }

    public String valueCall() {
        return ("http://api.walmartlabs.com/v1/vod?format=json&apiKey=" + APIkey);
    }

    public void dealCall() throws IOException {
        try {
            url = new URL(valueCall());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String temp;

        while ((temp = in.readLine()) != null) {
            dealResponse += temp;
        }
        dealCallResponse = dealResponse;
    }

    public String getNamedeal() {
        String str = dealCallResponse;
        int indName = str.indexOf("name");
        StringTokenizer par = new StringTokenizer(str.substring(indName), ",");
        //System.out.println("The Name: "+par.nextToken().split(":")[1].replace("\"", ""));
        return par.nextToken().split(":")[1].replace("\"", "");
    }

    public double getPricedeal() {
        String str = dealCallResponse;
        int indPrice = str.indexOf("salePrice");
        //next is the sales price
        StringTokenizer par = new StringTokenizer(str.substring(indPrice), ",");
        //System.out.println("The Price: $"+par.nextToken().split(":")[1].replace("\"", ""));
        return Double.parseDouble(par.nextToken().split(":")[1].replace("\"", ""));

    }

}

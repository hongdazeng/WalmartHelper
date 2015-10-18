import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * Created by Hongda Zeng on 10/17/2015!
 */
public class APIcaller {

    String productName;
    String newCall;
    URL url;

    String response = "";
    double upc;

    String doCallResponse;

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
                "query=" + productName + "&format=json&apiKey=34ktn2ap7hfenq9xv9ry8gv2");
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

    public double getUPC() throws IOException {

        String callResponse = doCallResponse;

        int start = callResponse.indexOf("upc");
        start += 6; //offset to start of upc
        String upcStr = callResponse.substring(start, start + 12);
        upc = Double.parseDouble(upcStr);

        return upc;
    }
    public String getName() throws IOException
    {
        String str = doCallResponse;
        int indName = str.indexOf("name");
        StringTokenizer par = new StringTokenizer(str.substring(indName),",");
		//System.out.println("The Name: "+par.nextToken().split(":")[1].replace("\"", ""));
		return par.nextToken().split(":")[1].replace("\"", "");
    }

    public double getPrice() throws Exception
    {
        String str = doCallResponse;
        int indPrice = str.indexOf("salePrice");
        //next is the sales price
		StringTokenizer par = new StringTokenizer(str.substring(indPrice),",");
		//System.out.println("The Price: $"+par.nextToken().split(":")[1].replace("\"", ""));
		return Double.parseDouble(par.nextToken().split(":")[1].replace("\"", ""));

    }

    public File getImage(String name)throws Exception
    {
        String str = doCallResponse;
        int indImage = str.indexOf("Image");
    	StringTokenizer par = new StringTokenizer(str.substring(indImage),",");
		String outImage = par.nextToken().split(":")[2].replace("\"", "").replace("//", "");
        ///write the file
        URL im = new URL("http://" + outImage);
        Image ou = ImageIO.read(im);
		BufferedImage fin = (BufferedImage) ou;
		File outputfile = new File(name);
		ImageIO.write(fin, "png", outputfile);
		return outputfile;
    }

}

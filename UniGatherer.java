import netscape.javascript.JSObject;

import javax.net.ssl.HttpsURLConnection;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class UniGatherer {

    class Uni{
        Point2D.Float latLng = new Point2D.Float();
        String name;

        public Uni(Point2D.Float pos, String name){
            this.latLng = pos;
            this.name = name;
        }
    }


    public static void main(String[] args){
        UniGatherer ug = new UniGatherer();
        ug.searchByRegion("NorthAmerica");

    }


    public UniGatherer(){

    }

    List<Uni> searchByRegion(String region){
        StringBuilder sb = new StringBuilder();
        RootishArrayStack unis = new RootishArrayStack<Uni>(Uni.class);



        try{
            URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?query=universities+in+"+region+"&key=AIzaSyCEEDi3d9JvpAqkAqXIDOFGcw5wwghza3E");
            HttpURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;

            while((line = bf.readLine()) != null){
                sb.append(line);
                System.out.println(line);
            }



            JsonReader jsonReader = Json.createReader(fis);

            Uni cu = new Uni(parseLatLng(jso.getJsonObject("Geometry")), jso.getString("name"));


            //System.out.println("Places Response = " + sb.toString());
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return unis;
    }

    Point2D.Float parseLatLng(JsonObject jso){

        JsonObject ijso = jso.getJsonObject("location");

        float lat = ijso.getFloat("lat");
        float lng = isjo.getFloat("lng");

        return new Point2D.Float(lat,lng);
    }

}

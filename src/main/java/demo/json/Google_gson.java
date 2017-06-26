package demo.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by caoyuncong on
 * 2017/6/26 20:22
 * JavaEE_1702.
 */
public class Google_gson {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //  Java object to JSON object
        Weather weather = new Weather("33", "50", "12");
        String json = gson.toJson(weather);
        System.out.println(json);
        // JSON object to JAVA object
        Weather weather1 = gson.fromJson(json, Weather.class);
        System.out.println(weather1);

    }
}

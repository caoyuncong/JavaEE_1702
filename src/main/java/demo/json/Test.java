package demo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoyuncong on
 * 2017/6/26 19:17
 * JavaEE_1702.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        Weather weather = new Weather("33", "50", "12");
        System.out.println(objectMapper.writeValueAsString(weather));

        List<Weather> weathers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            weathers.add(new Weather("tmp" + i, "hum" + i, "spd" + i));
        }
        System.out.println(objectMapper.writeValueAsString(weathers));

        Weather newWeather = objectMapper.readValue("{\"tmp\":\"33\",\"hum\":\"50\",\"spd\":\"12\"}", Weather.class);
        System.out.println(newWeather);
//        JSONObject jsonObject = new JSONObject(weather);
//        String json = jsonObject.toString(4);
//        System.out.println(json);
//
//        JSONArray jsonArray = new JSONArray(weathers);
//        String json1 = jsonArray.toString(4);
//        System.out.println(json1);


    }
}

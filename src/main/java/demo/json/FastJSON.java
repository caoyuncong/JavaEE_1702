package demo.json;

import com.alibaba.fastjson.JSON;

/**
 * Created by caoyuncong on
 * 2017/6/26 20:35
 * JavaEE_1702.
 */
public class FastJSON {
    public static void main(String[] args) {
        Weather weather = new Weather("37", "50", "29");
        // Java Object to JSON object

        String json = JSON.toJSONString(weather, true);
        System.out.println(json);
        // JSON object to Java object

        Weather weather1 = JSON.parseObject(json, Weather.class);
        System.out.println(weather1);

    }
}

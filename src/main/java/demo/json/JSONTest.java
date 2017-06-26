package demo.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by caoyuncong on
 * 2017/6/26 20:42
 * JavaEE_1702.
 */
public class JSONTest {
    public static void main(String[] args) {
        User user = new User(1, "user@gmail.com", "123");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            users.add(new User(i, "user" + i + "@gmail.com", "123"));
        }

        String jsonObjectString, jsonArrayString;

        System.out.println("\n--- json.org ---\n");

        JSONObject jsonObject = new JSONObject(user);
        // java object to json object
        System.out.println(jsonObject.toString(4));

        JSONArray jsonArray = new JSONArray(users);
        // java collection to json array
        System.out.println(jsonArray.toString(4));

        System.out.println("\n--- jackson ---\n");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            // java object to json object
            jsonObjectString = objectMapper.writeValueAsString(user);
            System.out.println(jsonObjectString);
            // java collection to json array
            jsonArrayString = objectMapper.writeValueAsString(users);
            System.out.println(jsonArrayString);
            // json object to java object
            System.out.println(objectMapper.readValue(jsonObjectString, User.class));
            // json array to java collection
            System.out.println(objectMapper.readValue(jsonArrayString, TypeFactory.defaultInstance().constructCollectionType(List.class, User.class))); //method1
            System.out.println(objectMapper.readValue(jsonArrayString, new TypeReference<List<User>>() {
            })); // method2
            System.out.println(objectMapper.readValue(jsonArrayString,objectMapper.getTypeFactory().constructCollectionType(List.class,User.class))); //method3
            System.out.println(Arrays.asList(objectMapper.readValue(jsonArrayString, User[].class))); // method4
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- gson ---\n");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // java object to json object
        jsonObjectString = gson.toJson(user);
        System.out.println(jsonObjectString);
        // java collection to json array
        jsonArrayString = gson.toJson(users);
        System.out.println(jsonArrayString);
        // json object to java object
        System.out.println(gson.fromJson(jsonObjectString, User.class));
        // json array to java collection
        System.out.println(gson.fromJson(jsonArrayString, new TypeToken<List<User>>(){}.getType())); //method 1
        System.out.println(gson.fromJson(jsonArrayString,ArrayList.class)); // method 2

        System.out.println("\n--- fastjson ---\n");
        // java object to json object
        jsonObjectString = JSON.toJSONString(user, true);
        System.out.println(jsonObjectString);
        // java collection to json array
        jsonArrayString = JSON.toJSONString(users, true);
        System.out.println(jsonArrayString);
        // json object to java object
        System.out.println(JSON.parseObject(jsonObjectString, User.class));
        // json array to java collection
        System.out.println(JSON.parseArray(jsonArrayString,User.class));
    }
}

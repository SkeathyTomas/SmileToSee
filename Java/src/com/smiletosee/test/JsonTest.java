package com.smiletosee.test;

import java.io.*;
import com.google.gson.*;
/**
 * Created by lgicl on 2017/4/22.
 */
public class JsonTest{
    public static void main(String[] args) throws Exception{
        JsonParser parse = new JsonParser();//创建json解析器
        JsonObject json = (JsonObject)parse.parse(new FileReader("F:/study/SRTP/SmileToSee/json.json"));//创建jsonObject对象
        double smile;
        smile = json.get("faces").getAsJsonArray().get(0).getAsJsonObject().get("attributes").getAsJsonObject().get("smile").getAsJsonObject().get("value").getAsDouble();
        System.out.print(smile);
    }
}

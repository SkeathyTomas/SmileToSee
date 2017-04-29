package com.smiletosee;

import java.io.*;

import com.megvii.cloud.http.*;
import org.json.*;
import com.google.gson.*;

public class LandMark {
	private final static String KEY = "9YQvHHQUUaOJjCRK3rWzfYQwRIEqnpt8";
	private final static String SECRET = "3lOFSZUeIxwr9yGyhoDe6B-bGBjGVb7_";
	CommonOperate face = new CommonOperate(KEY,SECRET);

	public String gender = "";
	public double smile;
	/*
	 * get normalized landmark
	 * @param originUrl 图片路径
	 * @param afterUrl 旋转后图片输出路径
	 * @return landmark标准化后的数组
	 * @throws Exception
	 */
	public double[] GetLandMark(String originUrl, String afterUrl) throws Exception{
		
		//get rollAngle and spin
		File file = new File(originUrl);
		byte[] buff = GetBytesFromFile.getBytesFromFile(file);
		Response response = face.detectByte(buff, 1, "");
		String result = new String(response.getContent());
		JSONObject json = new JSONObject(result);
		FaceResult faceResult = new FaceResult(json);
		double left[] = new double [2];
		double right[] = new double [2];
		left[0] = faceResult.get("faces").get(0).get("landmark").get("contour_left1").get("x").toDouble();
		left[1] = faceResult.get("faces").get(0).get("landmark").get("contour_left1").get("y").toDouble();
		right[0] = faceResult.get("faces").get(0).get("landmark").get("contour_right1").get("x").toDouble();
		right[1] = faceResult.get("faces").get(0).get("landmark").get("contour_right1").get("y").toDouble();
		double rollAngle = Math.toDegrees(Math.atan((right[1]-left[1])/(right[0]-left[0])));
		ImageDeal imageDeal = new ImageDeal(originUrl,afterUrl,"spin","jpg");
		imageDeal.spin(-rollAngle);
		
		//get landmark
		File spinFile = new File(afterUrl+"spin.jpg");
		buff = GetBytesFromFile.getBytesFromFile(spinFile);
		spinFile.delete();
		response = face.detectByte(buff, 1, "gender,smiling");
		result = new String(response.getContent());
		json = new JSONObject(result);
		faceResult = new FaceResult(json);
		double landmark[] = new double[166];
		for(int i=0;i<166;i=i+2){
			landmark[i] = faceResult.get("faces").get(0).get("landmark").get(Word.landMark[i/2]).get("x").toDouble();
			landmark[i+1] = faceResult.get("faces").get(0).get("landmark").get(Word.landMark[i/2]).get("y").toDouble();
		}
		
		//normalize
		//contour_left1作为参考原点，脸部最宽作为参考长度（contour_right1, contour_left1横坐标之差）
		double width = landmark[20]/*contour_right1.x*/ - landmark[2]/*contour_left1.x*/;
		double normLandmark[] = new double[166];
		for(int i=0;i<166;i=i+2){
			normLandmark[i] = (landmark[i] - landmark[2])/width;
			normLandmark[i+1] = (landmark[i+1] - landmark[3])/width;
		}

		gender = faceResult.get("faces").get(0).get("attributes").get("gender").get("value").toString();//获得性别信息
		JsonParser parser = new JsonParser();
		JsonObject gjson;
		gjson = (JsonObject)parser.parse(result);
		smile = gjson.get("faces").getAsJsonArray().get(0).getAsJsonObject().get("attributes").getAsJsonObject().get("smile").getAsJsonObject().get("value").getAsDouble();//获得微笑信息

		return normLandmark;
	}

}

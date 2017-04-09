package com.smiletosee;

import java.io.File;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.facepp.result.FaceppResult;

public class LandmarkGet {
	//basic parameter
	private final static String KEY="b210f733729472d3dd7c2b69827ba0d5";
	private final static String SECRET="bzxnqNn_LT5MuF9CtcvKo1Xe3MmfoRoX";
//	private final static String url="http://www.faceaface-paris.com/wp-content/uploads/2015/07/carre_homme.jpg";
	private final static String path="F:/study/SRTP/face++/face6.jpg";
//	private final static String faceId="d639f3e89cd2aa6a9aff4489fec67751";
	
	public static void main(String[] args) throws FaceppParseException{
		//set key, secret and img
		HttpRequests httpRequests = new HttpRequests(KEY, SECRET);
		PostParameters postParameters = new PostParameters()/*.setUrl(url).setAttribute("all")*/;
		postParameters.setImg(new File(path));
		
		//detect api
		FaceppResult result=httpRequests.request("detection", "detect", postParameters);
		//method2
//		FaceppResult result = httpRequests.detectionDetect(postParameters);		
		//get face_id
		String faceId=result.get("face").get(0).get("face_id").toString();
		postParameters.setFaceId(faceId);
		//get and store landmark data with landmark api
		FaceppResult result1=httpRequests.request("detection", "landmark", postParameters);
		double[] contour_left1 = {result1.get("result").get(0).get("landmark").get("contour_left1").get("x").toDouble(),
				result1.get("result").get(0).get("landmark").get("contour_left1").get("y").toDouble()};
//		double[] contour_left2 = {result1.get("result").get(0).get("landmark").get("contour_left2").get("x").toDouble(),
//				result1.get("result").get(0).get("landmark").get("contour_left2").get("y").toDouble()};
		System.out.println(contour_left1[1]);
	}

}

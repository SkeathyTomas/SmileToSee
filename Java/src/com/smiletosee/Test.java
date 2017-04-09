package com.smiletosee;

import java.io.*;

import com.megvii.cloud.http.*;
import org.json.*;

public class Test {
	private static String originUrl = "F:/study/SRTP/模型训练/人脸样本1.0/女/";
	private static String afterUrl = "F:/study/SRTP/模型训练/人脸样本1.0/女/";
	
	public static void main(String[] args) throws Exception{

		LandMark test = new LandMark();
		double landMark[] = new double[166];

		File data = new File("F:/study/SRTP/模型训练/data.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(data));

		landMark = test.Get(originUrl+6+".jpg", afterUrl);//指定图片
		for(int j=0;j<166;j=j+2){
			writer.write(String.valueOf(landMark[j])+",");
//			System.out.print(landMark[j]+" ");
			writer.write(String.valueOf(landMark[j+1])+",");
		}
		writer.newLine();
		writer.close();
	}
	
}

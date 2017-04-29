package com.smiletosee.model;

import com.smiletosee.LandMark;
import com.smiletosee.Word;

import java.awt.*;
import java.io.*;

public class ModelData {
	private static String originUrl = "F:/study/SRTP/模型训练/人脸样本1.0/女/";
	private static String afterUrl = "F:/study/SRTP/模型训练/人脸样本1.0/女/";

	public static void main(String [] args) throws Exception{
		LandMark test = new LandMark();
		double normLandmark[][] = new double[34][166];

//		csv output
		File data = new File("F:/study/SRTP/模型训练/data.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(data));
		for(int i=0;i<83;i++){
			writer.write(Word.landMark[i]+".x"+",");
			writer.write(Word.landMark[i]+".y"+",");
		}
		writer.newLine();
		writer.flush();

		Robot r = new Robot();
		for(int i=16;i<35;i++){
			normLandmark[i-1] = test.GetLandMark(originUrl+i+".jpg", afterUrl);
			for(int j=0;j<166;j=j+2){
				writer.write(String.valueOf(normLandmark[i-1][j])+",");
				writer.write(String.valueOf(normLandmark[i-1][j+1])+",");
			}
			writer.newLine();
			writer.flush();
			System.out.println(i);
			r.delay(6000);
		}

		writer.close();
	}

}
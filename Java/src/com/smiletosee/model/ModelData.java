package com.smiletosee.model;

import com.smiletosee.LandMark;
import com.smiletosee.Word;

import java.io.*;

public class ModelData {
	private static String originUrl = "F:\\study\\SRTP\\模型训练\\其他\\";
	private static String afterUrl = "F:\\study\\SRTP\\模型训练\\其他\\";
	
	public static void main(String [] args) throws Exception{
		LandMark test = new LandMark();
		double normLandmark[][] = new double[4][166];
		
//		csv output
		File data = new File("F:/study/SRTP/模型训练/data.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(data));
		for(int i=0;i<83;i++){
			writer.write(Word.landMark[i]+".x"+",");
			writer.write(Word.landMark[i]+".y"+",");
		}
		writer.newLine();
		writer.flush();
		
		for(int i=1;i<5;i++){
			normLandmark[i-1] = test.Get(originUrl+i+".jpg", afterUrl);
			for(int j=0;j<166;j=j+2){
				writer.write(String.valueOf(normLandmark[i-1][j])+",");
				writer.write(String.valueOf(normLandmark[i-1][j+1])+",");
			}
			writer.newLine();
			writer.flush();
		}
		
		writer.close();
	}

}

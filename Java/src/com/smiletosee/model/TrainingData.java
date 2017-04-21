package com.smiletosee.model;

import com.csvreader.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by lgicl on 2017/4/15.
 */
public class TrainingData {
    public static void main(String [] args) throws Exception {
        String path = "F:/study/SRTP/模型训练/classified/全/eyebrow/eyebrow.csv";
        ArrayList<String[]> csvList = new ArrayList<String[]>();//储存csv中读取数据

        //从csv文件中读取数据
        CsvReader reader = new CsvReader(path);
        reader.readHeaders();
        while (reader.readRecord()){
            csvList.add(reader.getValues());
        }
        reader.close();

        File out = new File("F:/study/SRTP/模型训练/classified/全/eyebrow/TrainingData.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));
        for (int i=0;i<64;i++){
            writer.write(csvList.get(i)[16]);
            writer.write(" ");
            for (int j=0;j<16;j++){
                writer.write(j+":");
                writer.write(csvList.get(i)[j]);
                writer.write(" ");
            }
            writer.newLine();
            writer.flush();
        }
        writer.close();
//        System.out.println(csvList.get(0)[16]);
    }

}

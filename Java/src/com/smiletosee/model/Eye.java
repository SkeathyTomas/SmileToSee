package com.smiletosee.model;

import com.csvreader.CsvReader;
import libsvm.*;

import java.util.ArrayList;

/**
 * Created by lgicl on 2017/4/21.
 */
public class Eye {
    public static void main(String [] args) throws Exception {
        String path = "F:/study/SRTP/模型训练/classified/全/eye/eye_select.csv";
        ArrayList<String[]> csvList = new ArrayList<String[]>();//储存csv中读取数据
        double lables[] = new double[30];//储存结果变量
        double data[][] = new double[30][18];//储存特征变量

        //从csv文件中选取训练集
        CsvReader reader = new CsvReader(path);
        reader.readHeaders();
        while (reader.readRecord()){
            csvList.add(reader.getValues());
        }
        reader.close();

        //结果变量
        for (int i=0;i<30;i++){
            lables[i] = Double.valueOf(csvList.get(i)[18]);
        }
        //特征变量
        for (int i=0;i<30;i++){
            for (int j=0;j<18;j++){
                data[i][j] = Double.valueOf(csvList.get(i)[j]);
            }
        }

        //模型训练
        svm_node[][]a = new svm_node[30][18];//特征变量
        for (int i=0;i<30;i++){
            for (int j=0;j<18;j++){
                a[i][j] = new svm_node();
                a[i][j].value = data[i][j];
            }
        }

        //定义problem对象
        svm_problem problem = new svm_problem();
        problem.l = 30;//向量数
        problem.x = a;
        problem.y = lables;
        //定义parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = 0;
        param.kernel_type = 2;
        param.cache_size = 40;
        param.C = 1;
        param.degree = 3;
        param.gamma = 1.0/18;
        param.eps = 0.1;

        System.out.println(svm.svm_check_parameter(problem, param));//参数检查
        svm_model model = svm.svm_train(problem,param);//模型训练
        svm.svm_save_model("model.txt",model);//模型输出

        for (int i=0;i<30;i++){
            System.out.print(svm.svm_predict(model,a[i])+" ");
        }
    }
}

package com.smiletosee;

import libsvm.*;
/**
 * Created by lgicl on 2017/4/21.
 */
public class FaceInfo {

//    private static String originURL = "F:/study/SRTP/形象生成/3.jpg";
    private static String originURL = "F:/study/SRTP/模型训练/人脸样本1.0/男/8.jpg";
    private static String afterURL = "F:/study/SRTP/形象生成/";

    public static String gender = "";
    public static double smile;

    public static int contour_type;
    public static int eyebrow_type;
    public static int eye_type;

    public static void main(String [] args) throws Exception{

        //基本变量定义
        double contour[] = new double[38];
        double eye[] = new double[18];
        double eyebrow[] = new double[16];

        svm_model model = new svm_model();

        svm_node contour_node[] = new svm_node[38];
        svm_node eyebrow_node[] = new svm_node[16];
        svm_node eye_node[] = new svm_node[18];

        //获取标准化过的83个关键点坐标
        LandMark face = new LandMark();
        double landMark[] = new double[166];
        landMark = face.GetLandMark(originURL,afterURL);
        //性别、微笑信息核获得
        gender = face.gender;//Male, Female
        smile = face.smile;

        //按照模型对小类的坐标点分别标准化与确定分类
        //contour,确定脸型
        for (int i=0;i<38;i++){
            contour[i] = landMark[i];
            contour_node[i] = new svm_node();
            contour_node[i].index = i;
            contour_node[i].value = contour[i];
        }
//        for (int i=0;i<38;i++)
//            System.out.print(contour[i] + " ");
//        System.out.println();
        model = svm.svm_load_model("F:/study/SRTP/模型训练/classified/全/contour/model.txt");
        contour_type = (int) svm.svm_predict(model,contour_node);
        System.out.println("contour: " + contour_type);

        //eyebrow
        for (int i=0;i<8;i++){
            eyebrow[i*2] = landMark[i*2+58] - landMark[62];
            eyebrow[i*2+1] = landMark[i*2+59] - landMark[63];
        }
        for (int i=0;i<16;i++){
            eyebrow_node[i] = new svm_node();
            eyebrow_node[i].index = i;
            eyebrow_node[i].value = eyebrow[i];
        }
//        for (int i=0;i<16;i++)
//            System.out.print(eyebrow[i] + " ");
//        System.out.println();
        model = svm.svm_load_model("F:/study/SRTP/模型训练/classified/全/eyebrow/model.txt");
        eyebrow_type = (int) svm.svm_predict(model,eyebrow_node);
        System.out.println("eyebrow: " + eyebrow_type);

        //eye
        for (int i=0;i<5;i++){
            eye[i*2] = landMark[i*2+38] - landMark[40];
            eye[i*2+1] = landMark[i*2+39] - landMark[41];
        }
        for (int i=5;i<9;i++){
            eye[i*2] = landMark[i*2+40] - landMark[40];
            eye[i*2+1] = landMark[i*2+41] - landMark[41];
        }
        for (int i=0;i<18;i++){
            eye_node[i] = new svm_node();
            eye_node[i].index = i;
            eye_node[i].value = eye[i];
        }
        for (int i=0;i<18;i++)
            System.out.print(eye[i] + " ");
        System.out.println();
        model = svm.svm_load_model("F:/study/SRTP/模型训练/classified/全/eye/model.txt");
        eye_type = (int) svm.svm_predict(model,eye_node);
        System.out.println("eye: " + eye_type);

        System.out.println(gender);
        System.out.println(smile);
    }
}

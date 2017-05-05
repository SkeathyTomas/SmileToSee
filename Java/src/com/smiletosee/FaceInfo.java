package com.smiletosee;

import libsvm.*;
/**
 * Created by lgicl on 2017/4/21.
 */
public class FaceInfo {

//    private static String originURL = "F:/study/SRTP/形象生成/3.jpg";
    private String afterURL = "F:/study/SRTP/形象生成/";

    public String gender = "";
    public double smile;

    public int contour_type;
    public int eyebrow_type;
    public int eye_type;

    public double eyebrowX, eyebrowY;
    public double eyeX, eyeY;
    public double mouthX, mouthY;


    public void GetFaceInfo(String imgURL) throws Exception{

        //基本变量定义
        double contour[] = new double[38];
        double eye[] = new double[18];
        double eyebrow[] = new double[16];

        //svm相关
        svm_model model = new svm_model();
        svm_node contour_node[] = new svm_node[38];
        svm_node eyebrow_node[] = new svm_node[16];
        svm_node eye_node[] = new svm_node[18];

        //获取标准化过的83个关键点坐标
        LandMark face = new LandMark();
        double landMark[] = new double[166];
        landMark = face.GetLandMark(imgURL,afterURL);

        //性别、微笑信息核获得
        gender = face.gender;//Male, Female
        smile = face.smile;

        //元素坐标获取与转换
        eyebrowX = (landMark[58] + landMark[66])/2;//left_eyebrow_left_corner.x,left_eyebrow_right_corner.x平均值
        eyebrowY = (landMark[63] + landMark[71])/2;//left_eyebrow_lower_middle.y,left_eyebrow_upper_middle.y平均值
        eyeX = landMark[40];//left_eye_center.x
        eyeY = landMark[41];//left_eye_center.y
        mouthX = (landMark[74] + landMark[92])/2;//mouth_left_corner.x,mouth_right_corner.x平均值
        mouthY = (landMark[77] + landMark[109])/2;//mouth_lower_lip_bottom.y,moth_upper_lip_top.y平均值


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
//        System.out.println("contour: " + contour_type);

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
//        System.out.println("eyebrow: " + eyebrow_type);

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
//        for (int i=0;i<18;i++)
//            System.out.print(eye[i] + " ");
//        System.out.println();
        model = svm.svm_load_model("F:/study/SRTP/模型训练/classified/全/eye/model.txt");
        eye_type = (int) svm.svm_predict(model,eye_node);
//        System.out.println("eye: " + eye_type);

//        System.out.println(gender);
//        System.out.println(smile);
    }

    public static void main(String[] args) throws Exception{
        FaceInfo faceInfo = new FaceInfo();
        faceInfo.GetFaceInfo("F:/study/SRTP/模型训练/人脸样本1.0/男/1.jpg");
//        System.out.println(faceInfo.mouthX);
//        System.out.println(faceInfo.mouthY);
    }
}

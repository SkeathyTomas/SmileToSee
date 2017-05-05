package com.smiletosee;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by lgicl on 2017/4/29.
 */
public class FaceCreate {
    private static int width = 300;
    private static int height = 300;

    static File out = new File("F:/study/SRTP/形象生成/out.png");

    public static void main(String args[]) throws Exception{

        //选取需要处理的人脸，获得基本信息
        FaceInfo faceInfo = new FaceInfo();
//        LandMark key = new LandMark();
//        key.SetKey("","");
        String input = "F:/study/SRTP/形象生成/3.jpg";
        faceInfo.GetFaceInfo(input);
//        System.out.println(faceInfo.contour_type);

        //人脸素材定位
        File faces[] = {
                new File("F:/study/SRTP/素材库/face/m1.png"),
                new File("F:/study/SRTP/素材库/face/m2.png"),
                new File("F:/study/SRTP/素材库/face/m3.png"),
                new File("F:/study/SRTP/素材库/face/f1.png"),
                new File("F:/study/SRTP/素材库/face/f2.png"),
                new File("F:/study/SRTP/素材库/face/f3.png")
        };
        File eyebrows[][] = {
                {new File("F:/study/SRTP/素材库/eyebrow/1l.png"),new File("F:/study/SRTP/素材库/eyebrow/1r.png")},
                {new File("F:/study/SRTP/素材库/eyebrow/2l.png"),new File("F:/study/SRTP/素材库/eyebrow/2r.png")},
                {new File("F:/study/SRTP/素材库/eyebrow/3l.png"),new File("F:/study/SRTP/素材库/eyebrow/3r.png")},
                {new File("F:/study/SRTP/素材库/eyebrow/4l.png"),new File("F:/study/SRTP/素材库/eyebrow/4r.png")}
        };
        File eyes[][] = {
                {new File("F:/study/SRTP/素材库/eye/m1l.png"),new File("F:/study/SRTP/素材库/eye/m1r.png")},
                {new File("F:/study/SRTP/素材库/eye/m2l.png"),new File("F:/study/SRTP/素材库/eye/m2r.png")},
                {new File("F:/study/SRTP/素材库/eye/m3l.png"),new File("F:/study/SRTP/素材库/eye/m3r.png")},
                {new File("F:/study/SRTP/素材库/eye/f1l.png"),new File("F:/study/SRTP/素材库/eye/f1r.png")},
                {new File("F:/study/SRTP/素材库/eye/f2l.png"),new File("F:/study/SRTP/素材库/eye/f2r.png")},
                {new File("F:/study/SRTP/素材库/eye/f3l.png"),new File("F:/study/SRTP/素材库/eye/f3r.png")}
        };
        File mouths[] = {
                new File("F:/study/SRTP/素材库/mouth/m1.png"),
                new File("F:/study/SRTP/素材库/mouth/m2.png"),
                new File("F:/study/SRTP/素材库/mouth/m3.png"),
                new File("F:/study/SRTP/素材库/mouth/f1.png"),
                new File("F:/study/SRTP/素材库/mouth/f2.png"),
                new File("F:/study/SRTP/素材库/mouth/f3.png")
        };
        File hairs[] = {
                new File("F:/study/SRTP/素材库/hair/hairman.png"),
                new File("F:/study/SRTP/素材库/hair/hairwoman.png")
        };

        //素材位置信息
        int eyebrowX, eyebrowY;
        int eyeX, eyeY;
        int mouthX, mouthY;

        //原始数据*比例放大+水平竖直修正
        eyebrowX = (int) (faceInfo.eyebrowX * 240) + 30;
        eyebrowY = (int) (faceInfo.eyebrowY *160) + 160;
        eyeX = (int) (faceInfo.eyebrowX * 240) + 30;
        eyeY = 0 + 160;
        mouthX = (int) (faceInfo.mouthX * 240) + 30;
        mouthY = (int) (faceInfo.mouthY * 160) + 160;

        //Image对象定义
        Image face, eyebrowL, eyebrowR, eyeL, eyeR, mouth,hair;

        //选取对应的人脸素材
//        face = ImageIO.read(faces[0]);
//        eyebrowL = ImageIO.read(eyebrows[0][0]);
//        eyebrowR = ImageIO.read(eyebrows[0][1]);
//        eyeL = ImageIO.read(eyes[0]);
//        mouth = ImageIO.read(mouths[0]);
//        hair = ImageIO.read(hairs[0]);
        //男性
        if(faceInfo.gender.equals("Male")){
            //脸型
            switch (faceInfo.contour_type){
                case 1:face = ImageIO.read(faces[0]);break;
                case 2:face = ImageIO.read(faces[1]);break;
                case 3:face = ImageIO.read(faces[2]);break;
                default:face = ImageIO.read(faces[0]);break;
            }
            //眉毛
            switch (faceInfo.eyebrow_type){
                case 1:eyebrowL = ImageIO.read(eyebrows[0][0]);eyebrowR = ImageIO.read(eyebrows[0][1]);break;
                case 2:eyebrowL = ImageIO.read(eyebrows[1][0]);eyebrowR = ImageIO.read(eyebrows[1][1]);break;
                case 3:eyebrowL = ImageIO.read(eyebrows[2][0]);eyebrowR = ImageIO.read(eyebrows[2][1]);break;
                case 6:eyebrowL = ImageIO.read(eyebrows[3][0]);eyebrowR = ImageIO.read(eyebrows[3][1]);break;
                default:eyebrowL = ImageIO.read(eyebrows[0][0]);eyebrowR = ImageIO.read(eyebrows[0][1]);break;
            }
            //眼睛
            switch (faceInfo.eye_type){
                case 1:eyeL = ImageIO.read(eyes[0][0]);eyeR = ImageIO.read(eyes[0][1]);break;
                case 2:eyeL = ImageIO.read(eyes[1][0]);eyeR = ImageIO.read(eyes[1][1]);break;
                case 4:eyeL = ImageIO.read(eyes[3][0]);eyeR = ImageIO.read(eyes[3][1]);break;
                case 5:eyeL = ImageIO.read(eyes[4][0]);eyeR = ImageIO.read(eyes[4][1]);break;
                case 7:eyeL = ImageIO.read(eyes[5][0]);eyeR = ImageIO.read(eyes[5][1]);break;
                case 8:eyeL = ImageIO.read(eyes[6][0]);eyeR = ImageIO.read(eyes[6][1]);break;
                default:eyeL = ImageIO.read(eyes[0][0]);eyeR = ImageIO.read(eyes[0][1]);break;
            }
            //嘴巴
            if (faceInfo.smile < 20)
                mouth = ImageIO.read(mouths[0]);
            else if (faceInfo.smile <70)
                mouth = ImageIO.read(mouths[1]);
            else
                mouth = ImageIO.read(mouths[2]);
            //头发
            hair = ImageIO.read(hairs[0]);
        }
        //女性
        else{
            //脸型
            switch (faceInfo.contour_type){
                case 1:face = ImageIO.read(faces[3]);break;
                case 2:face = ImageIO.read(faces[4]);break;
                case 3:face = ImageIO.read(faces[5]);break;
                default:face = ImageIO.read(faces[3]);break;
            }
            //眉毛
            switch (faceInfo.eyebrow_type){
                case 1:eyebrowL = ImageIO.read(eyebrows[0][0]);eyebrowR = ImageIO.read(eyebrows[0][1]);break;
                case 2:eyebrowL = ImageIO.read(eyebrows[1][0]);eyebrowR = ImageIO.read(eyebrows[1][1]);break;
                case 3:eyebrowL = ImageIO.read(eyebrows[2][0]);eyebrowR = ImageIO.read(eyebrows[2][1]);break;
                case 6:eyebrowL = ImageIO.read(eyebrows[3][0]);eyebrowR = ImageIO.read(eyebrows[3][1]);break;
                default:eyebrowL = ImageIO.read(eyebrows[0][0]);eyebrowR = ImageIO.read(eyebrows[0][1]);break;
            }
            //眼睛
            switch (faceInfo.eye_type){
                case 1:eyeL = ImageIO.read(eyes[0][0]);eyeR = ImageIO.read(eyes[0][1]);break;
                case 2:eyeL = ImageIO.read(eyes[1][0]);eyeR = ImageIO.read(eyes[1][1]);break;
                case 4:eyeL = ImageIO.read(eyes[3][0]);eyeR = ImageIO.read(eyes[3][1]);break;
                case 5:eyeL = ImageIO.read(eyes[4][0]);eyeR = ImageIO.read(eyes[4][1]);break;
                case 7:eyeL = ImageIO.read(eyes[5][0]);eyeR = ImageIO.read(eyes[5][1]);break;
                case 8:eyeL = ImageIO.read(eyes[6][0]);eyeR = ImageIO.read(eyes[6][1]);break;
                default:eyeL = ImageIO.read(eyes[0][0]);eyeR = ImageIO.read(eyes[0][1]);break;
            }
            //嘴巴
            if (faceInfo.smile < 20)
                mouth = ImageIO.read(mouths[3]);
            else if (faceInfo.smile <70)
                mouth = ImageIO.read(mouths[4]);
            else
                mouth = ImageIO.read(mouths[5]);
            //头发
            hair = ImageIO.read(hairs[1]);
        }

        //绘图
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bi.createGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,width,height);//白色背景
        graphics.drawImage(face,0,0,null);//画底脸
        graphics.drawImage(eyebrowL,eyebrowX-30,eyebrowY-10,null);//眉毛
        graphics.drawImage(eyebrowR,300-eyebrowX-30,eyebrowY-10,null);
        graphics.drawImage(eyeL,eyeX-30,eyeY-10,60,30,null);//眼睛
        graphics.drawImage(eyeR,300-eyeX-30,eyeY-10,null);
        graphics.drawImage(mouth,mouthX-30,mouthY-20,60,40,null);//嘴巴
        graphics.drawImage(hair,0,0,null);//头发

        //输出
        graphics.dispose();
        ImageIO.write(bi,"png",out);

    }

}

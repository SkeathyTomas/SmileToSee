package com.smiletosee.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by lgicl on 2017/4/29.
 */
public class ImageAdd {

    public static void main(String[] args) throws Exception{
        Image("F:/study/SRTP/素材库/eyebrows/maneyebrow.png","F:/study/SRTP/素材库/faces/bigroundface.png",20,20);
    }

    public static void Image(String adds, String target, int x, int y) throws Exception {

        //五官元件
        File ele = new File(adds);
        Image src = ImageIO.read(ele);
        int width = src.getWidth(null);
        int height = src.getHeight(null);

        //底脸
        File back = new File(target);
        Image backSrc = ImageIO.read(back);

        //绘制脸部底图
        BufferedImage image = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(backSrc,0,0,300,300,null);
        //绘制五官
        graphics.drawImage(src,x,y,width,height,null);

        //图片输出
        graphics.dispose();
        ImageIO.write(image,"jpg",new File("F:/study/SRTP/形象生成/test.jpg"));
    }
}



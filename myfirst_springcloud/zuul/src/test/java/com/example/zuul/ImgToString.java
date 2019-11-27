package com.example.zuul;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;

public class ImgToString {
    public static void main(String[] args) {
        createAsciiPic("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\1569310073(1).jpg");
//        char ca[]={' ','.','_',';','/','o','e','h','k','f','g','$','#','@'};
//        ca=fz(ca);
//        StringBuffer str=new StringBuffer();
//        Color color=null;
//        File file=new File("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\badapply.jpg");
//        try {
//           BufferedImage imgbuff= ImageIO.read(file);
//           //imgbuff=yasuo(imgbuff);
//           int imgH=imgbuff.getHeight(),
//               imgW=imgbuff.getWidth();
//            System.out.println(imgH+","+imgW);
//            for (int y = 0; y < imgH; y++) {
//                for (int x = 0; x < imgW; x++) {
//                    int rgb=imgbuff.getRGB(x,y);
//                    color=new Color(rgb);
//                    float val=(color.getRed()*299 + color.getGreen()*587 + color.getBlue()*114) / 1000,
//                        index=(val*(ca.length-1))/255;
//                    //System.out.println(color.getRed()+","+color.getGreen()+","+color.getBlue());
//                    str.append(ca[Math.round(index)]);
//                }
//                str.append("\n");
//            }
//            PrintStream ps=new PrintStream("C:\\Users\\Administrator\\Desktop\\log.txt");
//            System.setOut(ps);
//            System.out.println(str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    public static void createAsciiPic(final String path) {
        //final String base = "\"@#&$%*o!;.";// 字符串由复杂到简单
        final String base = "#8XOHLTI)i=+;:,. ";// 字符串由复杂到简单
        try {
            BufferedImage image = ImageIO.read(new File(path));  //读取图片
            //image=yasuo(image);
            //image=resize(image,0,0);
            //输出到指定文件中
            final StringBuffer str = new StringBuffer();
            for (int y = 0; y < image.getHeight(); y += 2) {
                for (int x = 0; x < image.getWidth(); x++) {
                    final int pixel = image.getRGB(x, y);
                    final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                    final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                    final int index = Math.round(gray * (base.length() + 1) / 255);
                    String s = index >= base.length() ? " " : String.valueOf(base.charAt(index));
                    str.append(s);
                }
                str.append("\n");
            }
            PrintStream ps=new PrintStream("C:\\Users\\Administrator\\Desktop\\log.txt");
            System.setOut(ps);
            System.out.println(str);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}

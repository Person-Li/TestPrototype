package com.dbservice.db;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
//        System.out.println(getCount("abcsfdggcscsbcsscsh","cs"));
//        System.out.println(System.getProperties());
        Test ts=new Test();
        StringBuffer data=Test.readTxt("C:\\Users\\Administrator\\Desktop\\videoToStr.txt");
        String[] list=data.toString().split("%");
        System.out.println(list[list.length-3]);
//        ts.videoPlay(Arrays.asList(list),ts.swing());
//        try {
//            ts.videoToString(new Test().swing());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static int getCount(String str,String yb){
        int count=0,
            index=0;
        while ((index=str.indexOf(yb,index))!=-1){
            index+=yb.length();
            count++;
        }
        return count;
    }
    public void videoToString(JTextArea text) throws IOException {
        List list=new ArrayList<StringBuffer>();
        FFmpegFrameGrabber fFmpegFrameGrabber=new FFmpegFrameGrabber("C:\\Users\\Administrator\\Videos\\aaa.mp4");
        fFmpegFrameGrabber.start();
        int fps=fFmpegFrameGrabber.getLengthInFrames(),
            i=0;
        System.out.println("视频帧一共:"+fps);
        Frame f=null;
        while (i<fps) {
            Frame a=fFmpegFrameGrabber.grabFrame();
            if(a!=null){
                BufferedImage img=new Java2DFrameConverter().getBufferedImage(a);
                if(img!=null) {
                    BufferedImage buffimg = yasuo(img);
                    list.add(createAsciiPic(buffimg));
                }
            }
            i++;
            text.setText("已转换"+i+"帧");
        }
        fFmpegFrameGrabber.stop();

    }
    public StringBuffer createAsciiPic(BufferedImage img) {
        //final String base = "\"@#&$%*o!;.";// 字符串由复杂到简单
        final String base = "#8XOHLTI)i=+;:,. ";// 字符串由复杂到简单
        BufferedImage image = img;  //读取图片
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
//            PrintStream ps=new PrintStream("C:\\Users\\Administrator\\Desktop\\log.txt");
//            System.setOut(ps);
        return str;
    }

    public static BufferedImage yasuo(BufferedImage img){

        int srcImageWidth = img.getWidth();
        int srcImageHeight = img.getHeight();
        //对截图进行等比例缩放(缩略图)
        int width = 170;
        int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
        BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        thumbnailImage.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        return thumbnailImage;
    }
    public void videoPlay(List list,JTextArea canvas){  //播线程
        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    canvas.setText(""+list.get(i));
                    try {
                        TimeUnit.MILLISECONDS.sleep(13);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread th1=new Thread(new Runnable() {          //写线程
            @Override
            public void run() {
                StringBuffer str = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    str.append(list.get(i));
                    str.append("%");
                }
                PrintStream ps= null;
                try {
                    ps = new PrintStream("C:\\Users\\Administrator\\Desktop\\videoToStr1.txt");
                    System.setOut(ps);
                    System.out.println(str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
        //th1.start();
    }
    public static StringBuffer readTxt(String txtPath) {
        File file = new File(txtPath);
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                    sb.append("\n");
                }
                return sb;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public JTextArea swing(){
        JFrame frame=new JFrame("显示窗口");    //创建Frame窗口
        JPanel jp=new JPanel();    //创建一个JPanel对象
        JTextArea jta=new JTextArea("",60,200);
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK);    //设置组件的背景色
        jta.setFont(new Font("楷体",Font.BOLD,13));    //修改字体样式
        jta.setBackground(Color.white);    //设置按钮背景色
        jta.setText("dasdasfasf");
        JScrollPane jsp=new JScrollPane(jta);    //将文本域放入滚动窗口
        Dimension size=jta.getPreferredSize();    //获得文本域的首选大小
        jsp.setBounds(110,90,size.width,size.height);
        jp.add(jsp);    //将JScrollPane添加到JPanel容器中
        frame.add(jp);    //将JPanel容器添加到JFrame容器中
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setSize(2000,2000);    //设置JFrame容器的大小
        frame.setVisible(true);
        return jta;
    }
}

package com.pms.base.util;
 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class WebChapterUtil {
	 /**  
     * 印章名称距中心点偏移量,按照y轴方向  
     */  
    private int nameOffset = 50;   
    /**  
     * 印章宽度  
     */  
    private int width = 200;   
    /**  
     * 印章高度  
     */  
    private int height = 200;   
    /**  
     * 印章中心标志(默认为五角星)外接圆半径  
     */  
    private float radius = 30;   
    /**  
     * 印章所属单位的起始角度,以6点钟方向为中心,向两个方向平均扩展  
     */  
    private float firmAngle = 180;   
    /**  
     * 印章名称  
     */  
    private String name = "123456789012";   
    /**  
     * 印章名称颜色  
     */  
    private Color nameColor = Color.RED;   
    /**  
     * 印章所属单位  
     */  
    private String firm = "华东三省业务专用章";   
    /**  
     * 印章所属单位颜色  
     */  
    private Color firmColor = Color.RED;   
    /**  
     * 印章名称字体信息  
     */  
    private Font nameFont = new Font("宋体", Font.PLAIN, 16);   
    /**  
     * 印章所属单位字体信息  
     */  
    private Font firmFont = new Font("宋体", Font.PLAIN, 24);   
    /**  
     * 单位字体的宽度缩放比率(百分比).此参数可以使字体看起来瘦长  
     */  
    private float firmScale = 1.0F;   
    /**  
     * 边框线宽  
     */  
    private float borderWidth = 3F;   
    /**  
     * 边框颜色  
     */  
    private Color borderColor = Color.RED;   
    /**  
     * 印章标记(默认为五角星)线宽  
     */  
    private float signBorderWidth = 3F;   
    /**  
     * 印章标记颜色  
     */  
    private Color signBorderColor = Color.RED;   
    /**  
     * 印章标记填充颜色  
     */  
    private Color signFillColor = Color.RED;   
    
    public  WebChapterUtil(int width,int height,String firm,String name,float radius){
    	this.width=width;
    	this.height=height;
    	this.nameOffset=height/4;
    	this.firm=firm;
    	this.name=name;
    	this.firmAngle=height;
    	this.radius=radius;
    }
  
    public void draw(Graphics2D g2d,int wd,int hgt) {   
        // 把绘制起点挪到圆中心点   
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        
        g2d.translate(wd / 2, hgt / 2);   
  
        Stroke stroke = g2d.getStroke();// 旧的线性   
        // 填充五角星   
        Polygon polygon = getPentaclePoints(radius);   
        if (signFillColor != null) {   
            g2d.setColor(signFillColor);   
            g2d.fill(polygon);   
        }   
  
        // 绘制五角星边框   
        g2d.setStroke(new BasicStroke(signBorderWidth));   
        g2d.setColor(signBorderColor);   
        g2d.draw(polygon);   
  
        // 绘制印章边框   
        g2d.setColor(borderColor);   
        g2d.setStroke(new BasicStroke(borderWidth));   
        g2d.drawOval(-width / 2, -height / 2, width, height);   
        g2d.setStroke(stroke);   
  
        // 绘制印章名称   
        g2d.setFont(nameFont);   
        g2d.setColor(nameColor);   
        FontMetrics fm = g2d.getFontMetrics();   
        int w = fm.stringWidth(name);// 名称宽度   
        int h = fm.getHeight();// 名称高度   
        int y = fm.getAscent() - h / 2;// 求得中心线经过字体的高度的一半时的字体的起绘点   
        g2d.drawString(name, -w / 2, y + nameOffset);   
  
        // 绘制印章单位   
        g2d.setFont(firmFont);   
        g2d.setColor(firmColor);   
        fm = g2d.getFontMetrics();   
        h = fm.getHeight();// 字高度   
  
        int count = firm.length();// 字数   
        int r = width / 2;// 半径,就假设此印章是个矩形,方便计算   
  
        float angle = (360 - firmAngle) / (count - 1);// 字间角度   
        float start = 90 + firmAngle / 2;// 以x轴正向为0,顺时针旋转   
        double vr = Math.toRadians(90);// 垂直旋转弧度   
        char[] chars = firm.toCharArray();   
        for (int i = 0; i < count; i++) {   
            char c = chars[i];// 需要绘制的字符   
            int cw = fm.charWidth(c);// 此字符宽度   
            float a = start + angle * i;// 现在角度   
  
            double radians = Math.toRadians(a);   
            g2d.rotate(radians);// 旋转坐标系,让要绘制的字符处于x正轴   
            float x = r - h;// 绘制字符的x坐标为半径减去字高度   
            // g2d.drawLine(0, 0, (int) x, 0);// debug   
            g2d.translate(x, 0);// 移动到此位置,此时字和x轴垂直   
            g2d.rotate(vr);// 旋转90度,让字平行于x轴   
            g2d.scale(firmScale, 1);// 缩放字体宽度   
            g2d.drawString(String.valueOf(c), -cw / 2, 0);// 此点为字的中心点   
            // 将所有设置还原,等待绘制下一个   
            g2d.scale(1 / firmScale, 1);   
            g2d.rotate(-vr);   
            g2d.translate(-x, 0);   
            g2d.rotate(-radians);   
        }   
         
    }   
  
    /**  
     * 获取具有指定半径外接圆的五角星顶点  
     *   
     * @param radius  
     *            圆半径  
     */  
    private Polygon getPentaclePoints(float radius) {   
        if (radius <= 0)   
            return null;   
        float lradius = radius * 0.381966f;// 根据radius求内圆半径   
        double halfpi = Math.PI / 180f;   
        Point[] points = new Point[10];   
        for (int i = 0; i < points.length; i++) {   
            if (i % 2 == 1)   
                points[i] = new Point(   
                        (int) (Math.sin(halfpi * 36 * i) * radius),   
                        (int) (Math.cos(halfpi * 36 * i) * radius));   
            else  
                points[i] = new Point(   
                        (int) (Math.sin(halfpi * 36 * i) * lradius),   
                        (int) (Math.cos(halfpi * 36 * i) * lradius));   
        }   
        Polygon polygon = new Polygon();   
        for (Point p : points) {   
            polygon.addPoint(p.x, p.y);   
        }   
        return polygon;   
    }   
  
    /**  
     * 导出此印章为透明背景的图片字节数组.  
     *   
     * @param format  
     *            图片类型,如果为null,则默认为png  
     * @return 数组  
     * @throws IOException  
     *             写出图像数据出现问题  
     */  
    public byte[] export2pic(String format) throws IOException {   
        int fix = 5;// 宽高修正,如果宽高就为图片宽高,可能边框线被切割   
        BufferedImage bi = new BufferedImage(getWidth() + fix * 2, getHeight()   
                + fix * 2, BufferedImage.TYPE_INT_ARGB);   
        Graphics2D g2d = bi.createGraphics();   
        g2d.translate(fix, fix);   
        this.draw(g2d,220,220);   
        ByteArrayOutputStream baos = new ByteArrayOutputStream();   
        ImageIO.write(bi, format == null ? "png" : format, baos);   
        return baos.toByteArray();   
    }   
  
    public int getWidth() {   
        return width;   
    }   
  
    public int getHeight() {   
        return height;   
    }   
    /*
    public static void main(String args[])
    { 
    	BufferedImage image = new BufferedImage(200, 200,     BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2d = image.createGraphics(); 
    	WebChapterUtil seal=new WebChapterUtil(150,150,"结算业务章","00000000012",20);
    	
    	image = g2d.getDeviceConfiguration().createCompatibleImage(200, 200, Transparency.TRANSLUCENT);
    	 
    	g2d.dispose();
    	 
    	g2d = image.createGraphics();
    	///seal.draw(g2d,160,160);
    	seal.paintComponent(g2d, "中信银行业务专用章","123456789012","是");
    	OutputStream outPutStream;
    	  try {
    		  ImageIO.write(image,"png",new File("E:/test.png"));
    	   outPutStream = new FileOutputStream(
    	         "E:\\Chart1.png");
    	  seal.export2pic("E:\\Chart11.png");
    	 //  JPEGImageEncoder encoder =
    	  //        JPEGCodec.createJPEGEncoder(outPutStream);
    	 //     encoder.encode(image);
    	      outPutStream.close();
    	  } catch (Exception e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
    	  }
    }
    */
    public void paintComponent(Graphics g,String message,String msg2,String isShowWx)
	{   
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		//绘制圆
		int radius = 90;
		Ellipse2D circle = new Ellipse2D.Double();
		 
		circle.setFrameFromCenter(CENTERX, CENTERY, CENTERX + radius, CENTERY + radius);
		g2.setStroke(new BasicStroke(borderWidth));   
		g2.draw(circle);
		
		//绘制中间的五角星
		if(isShowWx.equals("是"))
		{
			Font starFont = new Font("宋体", Font.BOLD, 60);
			g2.setFont(starFont); 
			int strWidth = g2.getFontMetrics().stringWidth("★");
			g2.drawString("★",CENTERX-strWidth / 2, CENTERY + 20);	
			
		}
		
		//绘制中间的五角星
		Font starFont1 = new Font("宋体", Font.BOLD, 20);
		g2.setFont(starFont1); 
	    int strWidth = g2.getFontMetrics().stringWidth(msg2);
		System.out.println(strWidth);
		 g2.drawString(msg2, CENTERX-strWidth / 2,  CENTERY + 50); 
		
		//根据输入字符串得到字符数组
		String[] messages2 = message.split("",0);
		String[] messages = new String[messages2.length-1];
		System.arraycopy(messages2,1,messages,0,messages2.length-1);
		
		//输入的字数
		int ilength = messages.length;
		
		//设置字体属性
		int fontsize = 20;
		Font f = new Font("Serif", Font.BOLD, fontsize);

		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);
		
		//字符宽度＝字符串长度/字符数
		double char_interval = (bounds.getWidth() / ilength);
		//上坡度
		double ascent = -bounds.getY();

		int first = 0,second = 0;
		boolean odd = false;
		if (ilength%2 == 1)
		{
			first = (ilength-1)/2;
			odd = true;
		}
		else
		{
			first = (ilength)/2-1;
			second = (ilength)/2;
			odd = false;
		}
		
		double radius2 = radius - ascent;
		double x0 = CENTERX;
		double y0 = CENTERY - radius + ascent;
		//旋转角度
		double a = 2*Math.asin(char_interval/(2*radius2));
		
		if (odd)
		{
			g2.setFont(f);
			g2.drawString(messages[first], (float)(x0 - char_interval/2), (float)y0);
			
			//中心点的右边
			for (int i=first+1;i<ilength;i++)
			{
				double aa = (i - first) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));
			}
			//中心点的左边
			for (int i=first-1;i>-1;i--)
			{
				double aa = (first - i) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
			}
			
		}
		else
		{
			//中心点的右边
			for (int i=second;i<ilength;i++)
			{
				double aa = (i - second + 0.5) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));
			}
			
			//中心点的左边
			for (int i=first;i>-1;i--)
			{
				double aa = (first - i + 0.5) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
			}
		}
		
	}
    
    //椭圆
    public void paintComponentTy(Graphics g,String message,String msg2,String isShowWx)
	{   
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		//绘制圆
		int radius = 95;
		Ellipse2D circle = new Ellipse2D.Double();
		 
		circle.setFrameFromCenter(CENTERX, CENTERY, CENTERX + radius, CENTERY/2 + radius);
		g2.setStroke(new BasicStroke(borderWidth));   
		g2.draw(circle);
		
		//绘制中间的五角星
		if(isShowWx.equals("是"))
		{
			Font starFont = new Font("宋体", Font.BOLD, 30);
			g2.setFont(starFont); 
			int strWidth = g2.getFontMetrics().stringWidth("★");
			g2.drawString("★",CENTERX-strWidth / 2, CENTERY /2+60);	
			
		}
		
		//绘制中间的五角星
		Font starFont1 = new Font("宋体", Font.BOLD, 20);
		g2.setFont(starFont1); 
	    int strWidth = g2.getFontMetrics().stringWidth(msg2);
		System.out.println(strWidth);
		 g2.drawString(msg2, CENTERX-strWidth / 2,  CENTERY/2 + 80); 
		
		//根据输入字符串得到字符数组
		String[] messages2 = message.split("",0);
		String[] messages = new String[messages2.length-1];
		System.arraycopy(messages2,1,messages,0,messages2.length-1);
		
		//输入的字数
		int ilength = messages.length;
		
		//设置字体属性
		int fontsize = 14;
		Font f = new Font("Serif", Font.BOLD, fontsize);

		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);
		
		//字符宽度＝字符串长度/字符数
		double char_interval = (bounds.getWidth() / ilength);
		//上坡度
		double ascent = -bounds.getY();

		int first = 0,second = 0;
		boolean odd = false;
		if (ilength%2 == 1)
		{
			first = (ilength-1)/2;
			odd = true;
		}
		else
		{
			first = (ilength)/2-1;
			second = (ilength)/2;
			odd = false;
		}
		
		double radius2 = radius - ascent;
		double x0 = CENTERX;
		double y0 = CENTERY - radius/2 + ascent;
		//旋转角度
		double a = 2*Math.asin(char_interval/(2*radius2));
		
		if (odd)
		{
			g2.setFont(f);
			g2.drawString(messages[first], (float)(x0 - char_interval/2), (float)y0);
			
			//中心点的右边
			for (int i=first+1;i<ilength;i++)
			{
				double aa = (i - first) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));
			}
			//中心点的左边
			for (int i=first-1;i>-1;i--)
			{
				double aa = (first - i) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
			}
			
		}
		else
		{
			//中心点的右边
			for (int i=second;i<ilength;i++)
			{
				double aa = (i - second + 0.5) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));
			}
			
			//中心点的左边
			for (int i=first;i>-1;i--)
			{
				double aa = (first - i + 0.5) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g2.setFont(f2);
				g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
			}
		}
		
	}
	public static final int CENTERX = 100;
   	public static final int CENTERY = 100;  
  
}  

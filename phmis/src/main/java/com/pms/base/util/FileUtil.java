package com.pms.base.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

//package com.santai.base.util;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.zip.GZIPInputStream;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.santai.rcm.stat.dao.iface.IStatCtrlBrAllDao;
//import com.santai.rcm.sys.manager.DeptManager;
//import com.santai.rcm.sys.vo.Dept;
//import com.santai.rcm.sys.vo.User;
//
public class FileUtil{
	private static Logger logger = Logger.getLogger(FileUtil.class);
	
	/**
	 * 将文件读成字符串
	 * @param fileName
	 * @return
	 */
	public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            logger.error("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }  
	
	public static void main(String[] args) {
		String str=FileUtil.readToString("D:\\项目\\代维管理平台\\上传结果协议\\LKFinish4028b981628fd39d01628fd81dea0095.json");
		System.out.println(str);
//		JSONArray jsonArray = JSONArray.fromObject(str);
		JSONObject jsonObject=JSONObject.fromObject(str);
		System.out.println(jsonObject.get("userMobile"));
	}
//	
//	private ApplicationContext context;
//	private static final String deptPath="F:\\santai\\四川中行\\";
//
//	public FileUtil(){
//		System.out.println("构造函数初始化spring的配置文件！");
//		context = new ClassPathXmlApplicationContext("spring-config/*.xml");
//	}
//	
//	/**
//	 * <li>读取TXT文件中的数据，将每条数据存入一个字符串数组中
//	 * @param is 上传文件的数据输入流
//	 * @return List<String[]> 上传的TXT文件中的数据集合
//	 * @throws IOException 
//	 */
//	public List<String[]> readTxtFileData(InputStream is) throws IOException{
//		List<String[]> list = new LinkedList<String[]>();
//		InputStreamReader isReader = new InputStreamReader(is,"GBK");
//
//		BufferedReader br = new BufferedReader(isReader);
//		String element = null;
//		
//		try {
//			while ((element = br.readLine()) != null) {
//					list.add(element.split("\\|"));
//			}
//		} catch (IOException e) {
//			throw e;
//		} finally{
//			try {
//				if(is != null) is.close();
//				if(br != null) br.close();
//				if(isReader != null) isReader.close();
//			} catch (IOException e) {
//				throw e;
//			}
//		}
//
//		return list;
//	}
//	
//	/**<li>name:类似这样的文件0100006D.k01.gz.201111010，文件的路径固定在某个地方。再调用txtFileToDept生成dept的list
//	 * @param name
//	 * @return
//	 */
//	public List<Dept> rendZipFile(String path,String name)throws FileNotFoundException,IOException{
//		System.out.println("文件路径："+path);
//		System.out.println("文件名：" + name);
//		name = name.substring(0, name.lastIndexOf("."));
//		System.out.println("去掉后缀后文件名：" + name);
//
//		FileInputStream fin;
//		GZIPInputStream gin;
//		
//		fin = new FileInputStream(path+name);
//		gin = new GZIPInputStream(fin);
//		List<Dept> deptList = this.txtFileToDept(gin);
//		if (fin != null) {
//			fin.close();
//		}
//		if (gin != null) {
//			gin.close();
//		}
//		return deptList;
//	}
//	
//	public static void main(String[] args) throws Exception {
//		
//		Timer timer = new Timer();
//		timer.schedule(new FileUtil(), 1000, 1200000);// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
//		while (true) {// 这个是用来停止此任务的,否则就一直循环执行此任务了
//			try {
//				int ch = System.in.read();
//				if (ch - 'c' == 0) {
//					timer.cancel();// 使用这个方法退出任务
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	
//	/**
//	 * <li>流里面的内容，构造成dept
//	 * @param is
//	 * @return
//	 * @throws IOException
//	 */
//	public List<Dept> txtFileToDept(InputStream is) throws IOException{
//		
//		List<Dept> deptList = new LinkedList<Dept>();
//		InputStreamReader isReader = new InputStreamReader(is,"GBK");
//		BufferedReader br = new BufferedReader(isReader);
//		String element = null;
//		
//		try {
//			while ((element = br.readLine()) != null) {
//				String[] strings = element.split("\\|");
//				if (strings.length>10) {
//					Dept dept = new Dept();
//					dept.setCasCode("");
//					dept.setDeptType("");
//					dept.setDeptNo(strings[3].trim());
//					dept.setSysCode(strings[3].trim());
//					dept.setDeptName(strings[11].replace("　", "").trim());
//					dept.setFax(strings[118].replace("　", "").trim());
//					dept.setRemark(strings[54].replace("　", "").trim());
//					deptList.add(dept);
//				}
//			}
//		} catch (IOException e) {
//			throw e;
//		} finally{
//			try {
//				if(is != null) {
//					is.close();
//				}
//				if(br != null){ 
//					br.close();
//				}
//				if(isReader != null){
//					isReader.close();
//				}
//			} catch (IOException e) {
//				throw e;
//			}
//		}
//
//		return deptList;
//	}
//	
//	
//	@Override
//	public void run() {
//		
//		DeptManager deptManager = (DeptManager)context.getBean("deptManager");
//		try {
//			System.out.println("读取导入的文件，构造成dept的list");
//			List<Dept> deptList = this.rendZipFile(FileUtil.deptPath,"0100006D.k01.gz.201111010");
//			System.out.println("生成List<Dept> deptList结束！");
//			
//			System.out.println("将读取的文件插入到数据库！");
//			deptManager.insertSysDpetBak(deptList);
//			System.out.println("生成List<Dept> deptList结束！");
//		} catch (IOException e) {
//			System.out.println("插入数据库结束！");
//			e.printStackTrace();
//			System.exit(0);
//		}
//		
//		try {
//			System.out.println("调用存储过程，修改dept的cas_code和dept_type!存储过程名称：proc_input_onesecond_dept");
//			deptManager.execProcedure("proc_input_onesecond_dept(?)", null,1);
//			System.out.println("存储过程执行完毕！");
//		} catch (Exception e) {
//			System.out.println("调用存储过程失败！");
//			e.printStackTrace();
//			System.exit(0);
//		}
//		
//	}
	

	/**获取文件大小 返回 KB 保留3位小数  没有文件时返回0
	 * @param filepath 文件完整路径，包括文件名
	 * @return
	 */
	public static Double getFilesize(String filepath){
		File backupath = new File(filepath);
		return Double.valueOf(backupath.length())/1000.000;
	}
	
	/**
	 * 创建目录
	 * @param destDirName目标目录名
	 * @return 
	 */
	public static Boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if(!dir.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
			return dir.getParentFile().mkdirs();		//不存在就全部创建
		}
		return false;
	}

	/**
	 * 删除文件
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 读取到字节数组0
	 * @param filePath //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			//System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.pms.base.util;
//package com.santai.base.util;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.zip.GZIPInputStream;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import com.santai.rcm.sys.manager.UserManager;
//import com.santai.rcm.sys.vo.User;
//
//public class UserInput extends TimerTask{
//
//	private static final String userPath="F:\\santai\\四川中行\\用户导入资料\\";
//	private static final int loopCount=3;//循环读取文件的次数
//	private static final int rowCount=200;//每次读取文件的行数
//	private ApplicationContext context;
//	
//	public UserInput(){
//		System.out.println("构造函数初始化spring的配置文件！");
//		context = new ClassPathXmlApplicationContext("spring-config/*.xml");
//	}
//	
//	public List<User> rendUserZipFile(String path,String name)throws FileNotFoundException,IOException{
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
//		List<User> userList = this.txtFileToUser(gin);
//		if (fin != null) {
//			fin.close();
//		}
//		if (gin != null) {
//			gin.close();
//		}
//		return userList;
//	}
//	long n=0;
//	/**<li>流里面的文件组成user对象，放入到List。
//	 * @param is
//	 * @return
//	 * @throws IOException
//	 */
//	public List<User> txtFileToUser(InputStream is) throws IOException{
//		List<User> userList = new LinkedList<User>();
//		InputStreamReader isReader = new InputStreamReader(is,"GBK");
//		BufferedReader br = new BufferedReader(isReader);
//		
//		String element = null;
//		int i=0;
//		long temp=0;
//		try {
//			while ((element = br.readLine()) != null) {
//				if (temp==n) {
//					String[] strings = element.split("\\|");
//					if (strings.length>10) {
//						User user = new User();
//						user.setDeptId(strings[6].trim());
//						user.setNo(strings[3].substring(9).trim());
//						user.setName(strings[33].replace("　", "").trim());
//						user.setPassword("123456");
//						user.setUseDate(UserInput.formDate("yyyy-MM-dd"));
//						user.setSex("1");
//						user.setMobile("");
//						user.setWorkPhone("");
//						user.setFamilyPhone("");
//						user.setEmail("");
//						user.setRemark("");
//						user.setIsLock("0");
//						user.setState("1");
//						user.setFirstMenuId("");
//						user.setPwdEditDate(UserInput.formDate("yyyy-MM-dd"));
//						user.setLastLoginDate(UserInput.formDate("yyyy-MM-dd"));
//						user.setComeFromSign("0");
//						userList.add(user);
//					}
//					n=n+element.getBytes().length;
//					i=i+1;
//					if (i>UserInput.rowCount-1) {
//						break;
//					}
//				}
//				temp=temp+element.getBytes().length;
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
//		return userList;
//	}
//	
//	@Override
//	public void run() {
//		UserManager userManager = (UserManager) context.getBean("userManager");
//		
//		String resultSign = "0";//存储过程执行失败
//		
//		for (int i = 0; i < UserInput.loopCount; i++) {
//			try {
//				System.out.println("读取导入的文件，构造成User的list");
//				List<User> userList = this.rendUserZipFile(UserInput.userPath,
//						"0100006D.k07.gz.201111010");
//				System.out.println("生成List<Dept> deptList结束！数量："
//						+ userList.size());
//
//				System.out.println("将读取的文件插入到数据库！");
//				int count = userManager.insertUserToUserBak(userList);
//				System.out.println("将List<User> userList数据插入sys_user_dept结束！");
//				userList.clear();
//				System.out.println("清理掉List<User> userList数据。");
//			} catch (IOException e) {
//				System.out.println("插入数据库结束！");
//				e.printStackTrace();
//				System.exit(0);
//			}
//			try {
//				System.out.println("调用存储过程，修改dept的cas_code和dept_type!存储过程名称：proc_input_onesecond_dept");
//				List<String> args = new ArrayList<String>();
//				args.add("0");
//				//proc_input_user存储过程名称，参数1是导入成功后是否删除sys_user_bak表数据，参数2是存储过程返回的执行结果
//				resultSign = userManager.execProcedure("proc_input_user(?,?)",
//						args,2);
//				System.out.println("存储过程执行完毕！");
//			} catch (Exception e) {
//				System.out.println("调用存储过程失败！");
//				e.printStackTrace();
//				System.exit(0);
//			}
//		}
//		
//		if (resultSign.equals("1")) {//存储过程执行成功
////			userManager.execProcedure("", args, outputArgsCount)
//		}
//		
//	}
//	
//	private static String formDate(String format){
//		SimpleDateFormat sf = new SimpleDateFormat(format);
//		Calendar calendar = Calendar.getInstance();
//		return sf.format(calendar.getTime());
//	}
//	
//	public static void main(String[] args) throws Exception {
//		
//		Timer timer = new Timer();
//		timer.schedule(new UserInput(), 1000, 12000000);// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
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
//}

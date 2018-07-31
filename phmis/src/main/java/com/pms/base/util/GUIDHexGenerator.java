package com.pms.base.util;
 

import java.net.InetAddress;

/**
 * GUID生成器，生成全球唯一id
 * 可靠性：如果同一ip的不同进程在同一时间装载本类则会出现灾难后果
 * 线程安全性：多线程安全

 * 使用方法：GUIDHexGenerator.getInstance().generateId();
 * 
 * @改编 孙刚
 *
 */
public class GUIDHexGenerator {
	
	/**
	 * 构造函数，之所以没有作成私有是为了兼顾老应用代码，因为老应用代码开发时，本类还没设计成单例
	 *
	 */
	public GUIDHexGenerator() {
	}

	/**
	 * generateId，生成Id
	 * 
	 * @return String 返回GUID字符串

	 * 
	 */
	public String generateId() {
		//		return new StringBuffer(36).append(format(getIP())).append(SEPERATOR)
		//				.append(format(getJVM())).append(SEPERATOR).append(
		//						format(getHiTime())).append(SEPERATOR).append(
		//						format(getLoTime())).append(SEPERATOR).append(
		//						format(getCount())).toString();
		String id= generateIdStringBuffer().toString();
		
		return id;
	}
	/**
	 * generateId，生成Id
	 * 
	 * @return StringBuffer 返回GUID字符串

	 * 
	 */
	public StringBuffer generateIdStringBuffer() {
		return new StringBuffer(36).append(IPString).append(JVMString).append(
				format(getHiTime())).append(format(getLoTime())).append(
				format(getCount()));

	}

	/**
	 * 单例对象的获取函数

	 * @return GUIDHexGenerator
	 */
	public static GUIDHexGenerator getInstance() {
		return instance;
	}

	private static GUIDHexGenerator instance = new GUIDHexGenerator();

	private  final int IP;
	 {
		int ipadd;
		try {
			ipadd = BytesHelper.toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	private  final String IPString = format(IP);

	private  short counter = (short) 0;

	private  final int JVM = (int) (System.currentTimeMillis() >>> 8);

	private  final String JVMString = format(JVM);

	/**
	 * 通过JVM使id好对于不同的JVM不重复，除非它们同时装载这个类

	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are
	 * Short.MAX_VALUE instances created in a millisecond)
	 */

	protected short getCount() {
		synchronized (GUIDHexGenerator.class) {
			counter++;
			if (counter < 0) {
				counter = 0;
			}
			return counter;
		}
	}

	/**
	 * IP地址使局域网内唯一，如果你用网卡物理号则全球唯一了：）

	 * 
	 */

	protected int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */

	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	//private static final String SEPERATOR = "";

	protected  String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}
/*
	public static void main(String[] args) throws Exception {
//		testSpeed();
		//testFunction();
	}
*/
	public static void testSpeed() throws Exception {
		GUIDHexGenerator.getInstance().generateIdStringBuffer();
		long time0;
		time0 = System.currentTimeMillis();

		for (long i = 0; i < 300000L; i++) {
			GUIDHexGenerator.getInstance().generateIdStringBuffer();
		}
		System.out.println(System.currentTimeMillis() - time0);
		
		
	}

	public static void testFunction() throws Exception {
		long time0;
		time0 = System.currentTimeMillis();
		for (long i = 0; i < 3L; i++) {
			new GUIDHexGenerator().generateId();
		}
		System.out.println(System.currentTimeMillis() - time0);
		time0 = System.currentTimeMillis();
		for (long i = 0; i < 30L; i++) {
			GUIDHexGenerator.getInstance().generateId();
		}
		System.out.println(System.currentTimeMillis() - time0);
		time0 = System.currentTimeMillis();
		Thread t1 = new Thread() {
			public void run() {
				for (int i = 0; i < 300; i++) {
					System.out.println(GUIDHexGenerator.getInstance()
							.generateId()
							+ "----t1");
				}
				System.out.println("thread1 finished:"
						+ System.currentTimeMillis());
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				for (int i = 0; i < 300; i++) {
					System.out.println(GUIDHexGenerator.getInstance()
							.generateId()
							+ "----t2");
				}
				System.out.println("thread2 finished:"
						+ System.currentTimeMillis());
			}
		};
		System.out.println("start thread:" + System.currentTimeMillis());
		t1.start();
		t2.start();

	}
}

class BytesHelper {
	private BytesHelper() {
	}

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	public static short toShort(byte[] bytes) {
		return (short) (((-(short) Byte.MIN_VALUE + (short) bytes[0]) << 8)
				- (short) Byte.MIN_VALUE + (short) bytes[1]);
	}

	public static byte[] toBytes(int value) {
		byte[] result = new byte[4];
		for (int i = 3; i >= 0; i--) {
			result[i] = (byte) ((0xFFl & value) + Byte.MIN_VALUE);
			value >>>= 8;
		}
		return result;
	}

	public static byte[] toBytes(short value) {
		byte[] result = new byte[2];
		for (int i = 1; i >= 0; i--) {
			result[i] = (byte) ((0xFFl & value) + Byte.MIN_VALUE);
			value >>>= 8;
		}
		return result;
	}
}

package com.pms.rcm.maintain.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Service;

import com.pms.rcm.maintain.vo.DiskBean;
import com.pms.rcm.maintain.vo.NetBean;

@Service("platResourceMonitorManager")
public class PlatResourceMonitorManager {

	/**
	 * 获取CPU占用率 按CPU内核数进行计算，汇总
	 * 
	 * @return
	 * @throws SigarException
	 */
	public double cpuRate() throws SigarException {
		Sigar sigar = new Sigar();
		return sigar.getCpuPerc().getCombined();
		/*CpuInfo infos[] = sigar.getCpuInfoList();
		CpuPerc precList[] = sigar.getCpuPercList();
		Double allCom = 0.0;
		int j = 0;
		for (int i = 0; i < infos.length; i++) {
			System.out.println("第" + (i + 1) + "块CPU的总使用率:    " + CpuPerc.format(precList[i].getCombined()));// 总的使用率
			j = i;
			allCom += precList[i].getCombined();
		}
		System.out.println("CPU总的使用率:    " + CpuPerc.format(allCom / j));// 总的使用率
		return CpuPerc.format(allCom / j);*/
	}

	/**
	 * 获取硬盘使用率
	 * 
	 * @return
	 * @throws SigarException
	 */
	// 盘符操作
	public List<DiskBean> diskRate() throws SigarException {
		Sigar sigar = new Sigar();
		FileSystem fslist[] = sigar.getFileSystemList();
		Map<String, String> resMap = new HashMap<>();
		List<DiskBean> list=new ArrayList<DiskBean>();
		for (int i = 0; i < fslist.length; i++) {
			FileSystem fs = fslist[i];
			if (fs.getType() == 2) {
				
				FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
				System.out.println("盘符名称:    " + fs.getDevName());
				resMap.put("name", fs.getDevName());
				DiskBean b=new DiskBean();
				b.setName(fs.getDevName());
				b.setUseage(String.format("%.2f", usage.getUsePercent() * 100)); 
				list.add(b);
			 
			}
		}
		return list;
	}

	/**
	 * 获取内存使用率
	 * 
	 * @param args
	 * @throws IOException
	 */
	public Mem memoryRate() throws SigarException {
		String memRate = "";
		try {
			Sigar sigar = new Sigar();
			Mem mem = sigar.getMem();
			return mem;
			///memRate =mem.// String.format("%.2f", mem.getUsedPercent());
			//System.out.println("内存使用率:    " + memRate);
		} catch (SigarException ex) {
			ex.printStackTrace();
			return null;
		}
 
	}
	
	

	/**
	 * 获取网络使用率
	 * 
	 * @throws Exception
	 */
	public  NetBean netRate() throws Exception {
		Sigar sigar = new Sigar();
		String ifNames[] = sigar.getNetInterfaceList();
		NetBean netBean=new NetBean(); 
		try{
			for (int i = 0; i < ifNames.length; i++) { 
				String name = ifNames[i];
				NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
				NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
				if(ifstat.getRxPackets() > 0 || ifstat.getTxPackets() > 0){
					netBean.setName(ifconfig.getAddress());
					netBean.setTxData(String.valueOf(ifstat.getTxBytes()));
					netBean.setRxData(String.valueOf(ifstat.getRxBytes())); 
				}
			}
		 }catch(Exception e){
			e.printStackTrace();
		 }
		return netBean;
		
			//System.out.println("网络设备名:    " + name);// 网络设备名
			//System.out.println("IP地址:    " + ifconfig.getAddress());// IP地址
			//System.out.println("子网掩码:    " + ifconfig.getNetmask());// 子网掩码
			//System.out.println("ifconfig.getFlags():    " + ifconfig.getFlags());// 子网掩码
			//if ((ifconfig.getFlags() & 1L) <= 0L) {
			//	System.out.println("!IFF_UP...skipping getNetInterfaceStat");2072911766
			//	continue;
			//}
			
			//System.out.println(name + "接收的总包裹数:" + ifstat.getRxPackets());// 接收的总包裹数
			//System.out.println(name + "发送的总包裹数:" + ifstat.getTxPackets());// 发送的总包裹数
			//System.out.println(name + "接收到的总字节数:" + ifstat.getRxBytes());// 接收到的总字节数
			//System.out.println(name + "发送的总字节数:" + ifstat.getTxBytes());// 发送的总字节数
			//System.out.println(name + "接收到的错误包数:" + ifstat.getRxErrors());// 接收到的错误包数
			//System.out.println(name + "发送数据包时的错误数:" + ifstat.getTxErrors());// 发送数据包时的错误数
			//System.out.println(name + "接收时丢弃的包数:" + ifstat.getRxDropped());// 接收时丢弃的包数
			//System.out.println(name + "发送时丢弃的包数:" + ifstat.getTxDropped());// 发送时丢弃的包数
		
	}

	public static void main(String[] args) throws IOException {
		PlatResourceMonitorManager pm = new PlatResourceMonitorManager();
		try {
		//	String aa = pm.cpuRate();
			//System.out.println("CPU使用率：" + aa);// cpu占用率
			System.out.println("-----------------------------------------------------" );
		//	String ab = pm.diskRate();
		//	System.out.pritln("磁盘使用率：" + ab);
			System.out.println("-----------------------------------------------------" );
			pm.memoryRate();
			System.out.println("n-----------------------------------------------------" );
			pm.netRate();
			
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}

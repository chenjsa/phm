package com.pms.rcm.maintain.vo;

import java.util.List;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Mem;

public class PlatResourceInfo {
	
	private Double cpuRate;
	private List<DiskBean> diskRate;
	private Mem memoryRate;
	private NetBean netRate;
	
	
	public Double getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(Double cpuRate) {
		this.cpuRate = cpuRate;
	}
	 
	public List<DiskBean> getDiskRate() {
		return diskRate;
	}
	public void setDiskRate(List<DiskBean> diskRate) {
		this.diskRate = diskRate;
	}
	
	public Mem getMemoryRate() {
		return memoryRate;
	}
	public void setMemoryRate(Mem memoryRate) {
		this.memoryRate = memoryRate;
	}
	public NetBean getNetRate() {
		return netRate;
	}
	public void setNetRate(NetBean netRate) {
		this.netRate = netRate;
	}
	

}

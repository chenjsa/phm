package com.pms.configure;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.util.TimeUtil;

import com.pms.base.common.ResponseBean;
import com.pms.base.util.DbFH;
import com.pms.base.util.FileUtil;
import com.pms.rcm.backup.manager.DbBackupLogManager;
import com.pms.rcm.backup.vo.DbBackupLog;

@Component
@EnableScheduling // 该注解必须要加
public class DbBackupQuartz {
	@Autowired
    private DbBackupLogManager dbBackupLogManager;
	public static final Logger logger = LoggerFactory.getLogger(DbBackupQuartz.class); 
	//@Scheduled(fixedRate = 1000*60*60*8)//每5秒执行一次：fixedRate=5000
	///@Scheduled(fixedDelayString = "${springQuartz.exeStartRemindEl}")  
	@Scheduled(cron = "${springQuartz.exeStartRemindEl}")
	public void backup() throws Exception {
		logger.info("备份数据库开始---》");
		try{
	 		String str = DbFH.getDbFH().backup("").toString();//调用数据库备份
			System.out.println(FileUtil.getFilesize(str));
			DbBackupLog entity=new DbBackupLog();
			entity.setBackupModel("自动备份");
			entity.setBackupName(str.substring(str.lastIndexOf("/")+1));
			entity.setBackupTime(new java.sql.Timestamp(System.currentTimeMillis()));
			entity.setBackupType("全量备份");
			entity.setFileSize(Double.valueOf(FileUtil.getFilesize(str)/1024).shortValue()+"M");
			entity.setStorePath(str); 
			entity.setBackupStatus("完成");
			dbBackupLogManager.insert(entity);		
			logger.info("DbBackupLog信息新增:"+entity.toString());	  
		}catch(Exception e){ 
			e.printStackTrace();
			logger.info("DbBackupLog信息操作错误:"+e.getLocalizedMessage());	 
		}  
		logger.info("备份数据库-------结束");
	}
}

package com.pms.configure;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pms.base.common.BusinessException;
import com.pms.base.util.TimeUtil; 

@Component
@EnableScheduling // 该注解必须要加
public class GeneratePlanExeQuartz {
	protected Logger logger = Logger.getLogger(this.getClass()); 
 
	
	/**
	 * 根据计划生产巡检任务；并判断是否发送任务下达通知，判断是否下达任务即将开始通知
	 * @throws Exception
	 */
	/*@Scheduled(cron = "${springQuartz.planToExeTimeEl}")
//	@Scheduled(cron = "0 0/30 * * * ?") // 每30分钟执行一次
    public void work() throws Exception {
		 logger.info("-----job信息，生成巡检任务，生成任务下达通知，任务开始通知的job开始执行，开始时间："+TimeUtil.getSystemOfDateByFormat("yyyy-MM-dd hh:mm:ss"));
		 
		//扫描srs_check_plan表，如果是否在配置范围内task_pre_generate_day参数，则生成plan_exe的；（注意判断是否已经生成了任务）
		List<SrsCheckPlan> list=srsCheckPlanManager.find("from SrsCheckPlan plan where plan.isDel='1'");//1:标示没有删除
		List<ParamSet> setList=paramSetManager.find("from ParamSet p where p.name='task_pre_generate_day'");
		int task_pre_generate_day=2;
		if(setList!=null && setList.size()==1){
			task_pre_generate_day=Integer.parseInt(setList.get(0).getCode());
		}
		
		String sysDateStr=TimeUtil.getSystemOfDateByFormat("yyyy-MM-dd");
		Date sysDate=TimeUtil.toUtilDateFromStrDateByFormat(sysDateStr, "yyyy-MM-dd");
		
		for(int i=0;i<list.size();i++){
			SrsCheckPlan plan=list.get(i);
			Date startDate=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextStartTime(), "yyyy-MM-dd");
			
			int betweenDay=TimeUtil.daysBetween(sysDate, startDate);
			//日巡检计划不在这里生成，在生成计划是已经生成了exe,record的数据；
			if(!plan.getPlanCycle().equals("1") && betweenDay>=0 && betweenDay<=task_pre_generate_day){//生成任务
				logger.info("生成第"+i+"条任务");
				//生成巡检任务（生成巡检任务的时候，aop切面会生成通知）
				srsCheckPlanManager.insertExeAndRecordByPlan(plan);
				plan.setPlanPreStartTime(plan.getPlanNextStartTime());
				plan.setPlanPreEndTime(plan.getPlanNextEndTime());
				plan.setPlanTheoryExeTimes(plan.getPlanTheoryExeTimes()+1);//生成巡检任务后，将计划表中应该执行次数加一
				//更新计划中的下次开始时间和下次结束时间;
				//周期类型：1，日，2：周，3：月，4：季度，5：半年，6：年
				if(plan.getPlanCycle().equals("2")){
					java.util.Date date=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextStartTime(), "yyyy-MM-dd");
					date=TimeUtil.dateAddMinus(date,Calendar.WEEK_OF_MONTH,1);
					plan.setPlanNextStartTime(TimeUtil.toStrDateFromUtilDateByFormat(date, "yyyy-MM-dd"));
					
					java.util.Date date2=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextEndTime(), "yyyy-MM-dd");
					date2=TimeUtil.dateAddMinus(date2,Calendar.WEEK_OF_MONTH,1);
					plan.setPlanNextEndTime(TimeUtil.toStrDateFromUtilDateByFormat(date2, "yyyy-MM-dd"));
					srsCheckPlanManager.update(plan);
				}else if(plan.getPlanCycle().equals("3")){
					java.util.Date date=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextStartTime(), "yyyy-MM-dd");
					date=TimeUtil.dateAddMinus(date,Calendar.DAY_OF_MONTH,1);
					plan.setPlanNextStartTime(TimeUtil.toStrDateFromUtilDateByFormat(date, "yyyy-MM-dd"));
					
					java.util.Date date2=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextEndTime(), "yyyy-MM-dd");
					date2=TimeUtil.dateAddMinus(date2,Calendar.DAY_OF_MONTH,1);
					plan.setPlanNextEndTime(TimeUtil.toStrDateFromUtilDateByFormat(date2, "yyyy-MM-dd"));
					srsCheckPlanManager.update(plan);
				}else if(plan.getPlanCycle().equals("4")){
					java.util.Date date=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextStartTime(), "yyyy-MM-dd");
					date=TimeUtil.dateAddMinus(date,Calendar.DAY_OF_MONTH,3);
					plan.setPlanNextStartTime(TimeUtil.toStrDateFromUtilDateByFormat(date, "yyyy-MM-dd"));
					
					java.util.Date date2=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextEndTime(), "yyyy-MM-dd");
					date2=TimeUtil.dateAddMinus(date2,Calendar.DAY_OF_MONTH,3);
					plan.setPlanNextEndTime(TimeUtil.toStrDateFromUtilDateByFormat(date2, "yyyy-MM-dd"));
					srsCheckPlanManager.update(plan);
					
//					throw new BusinessException("增加一个季度未实现");
				}else if(plan.getPlanCycle().equals("5")){
					java.util.Date date=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextStartTime(), "yyyy-MM-dd");
					date=TimeUtil.dateAddMinus(date,Calendar.DAY_OF_MONTH,6);
					plan.setPlanNextStartTime(TimeUtil.toStrDateFromUtilDateByFormat(date, "yyyy-MM-dd"));
					
					java.util.Date date2=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextEndTime(), "yyyy-MM-dd");
					date2=TimeUtil.dateAddMinus(date2,Calendar.DAY_OF_MONTH,6);
					plan.setPlanNextEndTime(TimeUtil.toStrDateFromUtilDateByFormat(date2, "yyyy-MM-dd"));
					srsCheckPlanManager.update(plan);
//					throw new BusinessException("增加半年未实现");
				}else if(plan.getPlanCycle().equals("6")){
					java.util.Date date=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextStartTime(), "yyyy-MM-dd");
					date=TimeUtil.dateAddMinus(date,Calendar.YEAR,1);
					plan.setPlanNextStartTime(TimeUtil.toStrDateFromUtilDateByFormat(date, "yyyy-MM-dd"));
					
					java.util.Date date2=TimeUtil.toUtilDateFromStrDateByFormat(plan.getPlanNextEndTime(), "yyyy-MM-dd");
					date2=TimeUtil.dateAddMinus(date2,Calendar.YEAR,1);
					plan.setPlanNextEndTime(TimeUtil.toStrDateFromUtilDateByFormat(date2, "yyyy-MM-dd"));
					srsCheckPlanManager.update(plan);
				}
			}
		}//生成任务结束
		
		
		
       logger.info("-----job信息，生成巡检任务，生成任务下达通知，任务开始通知的job完成，完成时间："+TimeUtil.getSystemOfDateByFormat("yyyy-MM-dd hh:mm:ss"));
    }
	
//	@Scheduled(fixedRate = 1000*60*60*8)//每5秒执行一次：fixedRate=5000
	@Scheduled(fixedDelayString = "${springQuartz.exeStartRemindEl}")  
	public void play() throws Exception {
		logger.info("运行任务即将开始的提醒通知");
		String sysDateStr = TimeUtil.getSystemOfDateByFormat("yyyy-MM-dd");
		Date sysDate = TimeUtil.toUtilDateFromStrDateByFormat(sysDateStr, "yyyy-MM-dd");
		// 看是否需要发送任务即将开始的通知
		List<ParamSet> setList2 = paramSetManager.find("from ParamSet p where p.name='task_pre_remind_day'");
		int task_pre_remind_day = 1;// 默认值
		if (setList2 != null && setList2.size() == 1) {
			task_pre_remind_day = Integer.parseInt(setList2.get(0).getCode());
		}
		// 状态1.未完成 2 待审核 3.返工 4 已归档 5 任务失效
		List<SrsCheckPlanExe> exeList = srsCheckPlanExeManager
				.find("from SrsCheckPlanExe exe where exe.planState in('1')");
		for (SrsCheckPlanExe exe : exeList) {
			if (exe.getPlanState().equals("1")) {
				Date startDate = TimeUtil.toUtilDateFromStrDateByFormat(exe.getPlanStartTime(), "yyyy-MM-dd");
				int betweenDay = TimeUtil.daysBetween(sysDate, startDate);
				if (betweenDay >= 0 && betweenDay <= task_pre_remind_day) {
					srsCheckPlanExeManager.insertNotesAnd(exe);
				}
			}
		}
		logger.info("运行任务即将开始的提醒通知-------结束");
	}
}*/}

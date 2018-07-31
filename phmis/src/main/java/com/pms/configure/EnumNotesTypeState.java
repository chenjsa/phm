package com.pms.configure;

public enum EnumNotesTypeState {

    STATE_DOWN(0,"巡检任务下达"),
    STATE_START(1,"巡检任务开始提醒"),
    STATE_CHECK_RESULT(2,"巡检结果审核反馈"),
    STATE_UP(3,"巡检结果上传通知"),
    STATE_TIME_OUT(4,"巡检任务逾期通知"),
    STATE_PLAN_ADD(5,"巡检计划制定通知"),
    STATE_PLAN_UPDATE(6,"巡检计划变更通知"),
    ;

    private Integer state;

    private String description;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    EnumNotesTypeState(Integer state, String description) {
        this.state = state;
        this.description = description;
    }
}

package org.taskManager;

import java.util.Date;
import java.util.Map;

public class Tasks implements Comparable<Tasks> {
    private Long taskId;
    private String taskName;
    private String taskDetails;
    private Date createdDate;
    private Date targetDate;
    private String status;

    public Tasks(Long taskId, String taskName, String taskDetails, Date createdDate, Date targetDate, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDetails = taskDetails;
        this.createdDate = createdDate;
        this.targetDate = targetDate;
        this.status = status;
    }

    public Tasks(String taskName, String taskDetails, Date chekDate, String status) {
        this.taskName = taskName;
        this.taskDetails = taskDetails;
        this.targetDate = chekDate;
        this.status = status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task Name: " + taskName +
                "\n Task ID=" + taskId +
                ",\n Name='" + taskName + '\'' +
                ",\n Details='" + taskDetails + '\'' +
                ",\n Created Date=" + createdDate +
                ",\n Target Date=" + targetDate +
                ",\n Status=" + status +
                "\n}\n";
    }

    @Override
    public int compareTo(Tasks o) {
        if (this.taskId > o.getTaskId())
            return 1;
        else if (this.taskId < o.getTaskId())
            return -1;
        else
            return 1;
    }
}

package org.taskManager;

import java.util.HashMap;

public class TaskData extends HashMap<String,Tasks> {
    TaskData(){}
    TaskData(HashMap<String, Tasks> taskList){
        super(taskList);
    }
    TaskData(String s, Tasks t){
        super.put(s,t);
    }

}

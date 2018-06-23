package com.vince7839.util;

import com.vince7839.entity.Job;
import com.vince7839.entity.Task;

public class JobBuilder {
	public static Integer CTS_ID = 1;
	public static Integer GTS_ID = 2;
	public static Integer VTS_ID = 3;
	public static Integer GSI_ID = 4;
	public static Integer CTSV_ID = 5;
	public static Integer PERFORMANCE_ID = 6;
	public static Job build(Task t,Integer testId) {
		Job j = new Job();
		j.setTaskId(t.getId());
		j.setTestId(testId);
		return j;
	}
}

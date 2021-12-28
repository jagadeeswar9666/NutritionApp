package com.app.nutritionapp.Service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.nutritionapp.Model.CaloryLog;

@Component
public interface ICaloryLogService {

	
	public List<CaloryLog> findUserByUsername(String userName);
	
	public CaloryLog addCaloryLog(CaloryLog caloryLog);
	public CaloryLog updateCaloryLog(CaloryLog caloryLog);
	public void removeCaloryLog(Integer calory_id);

	public double sumOfCalories(String userName);
	public List<CaloryLog> getAllCaloryLog();
}

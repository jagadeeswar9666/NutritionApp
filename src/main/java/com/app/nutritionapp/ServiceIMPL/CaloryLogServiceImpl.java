package com.app.nutritionapp.ServiceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.nutritionapp.Model.CaloryLog;
import com.app.nutritionapp.Repository.ICaloryLogRepository;
import com.app.nutritionapp.Service.ICaloryLogService;


@Service("caloryLogService")
public class CaloryLogServiceImpl implements ICaloryLogService{
	
	@Autowired
	private ICaloryLogRepository caloryLogRepository;

	@Override
	public List<CaloryLog> findUserByUsername(String userName) {
		return caloryLogRepository.findByName(userName);
	}

	@Override
	public CaloryLog addCaloryLog(CaloryLog caloryLog) {
		return caloryLogRepository.save(caloryLog);
	}

	@Override
	public CaloryLog updateCaloryLog(CaloryLog caloryLog) {
		return caloryLogRepository.save(caloryLog);
	}

	@Override
	public void removeCaloryLog(Integer calory_id) {
          caloryLogRepository.deleteById(calory_id);		
	}

	@Override
	public double sumOfCalories(String userName) {
		return caloryLogRepository.findSum(userName);
	}
	
	@Override
	public List<CaloryLog> getAllCaloryLog() {
		return caloryLogRepository.findAll();
	}

}

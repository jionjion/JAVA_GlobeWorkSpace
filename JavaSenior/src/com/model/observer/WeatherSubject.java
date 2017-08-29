package com.model.observer;
/**
 * 	目标对象,知道观察者是谁,提供给观察者相应的添加/删除接口
 */

import java.util.ArrayList;
import java.util.List;

public abstract class WeatherSubject {

	/**保存订阅天气预报的人*/
	private List<WeatherObserver> observers = new ArrayList<WeatherObserver>();
	
	/**添加订阅天气的人*/
	public void attach(WeatherObserver observer) {
		observers.add(observer);
	}
	
	/**删除订阅天气的人*/
	public void detach(WeatherObserver observer) {
		observers.remove(observer);
	}
	
	/**更新天气的状态*/
	protected void notifyObserver() {
		for(WeatherObserver observer : observers) {
			observer.update(this);
		}
	}
}

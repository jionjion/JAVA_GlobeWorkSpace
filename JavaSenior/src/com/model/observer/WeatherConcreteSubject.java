package com.model.observer;
/**
 *	具体的目标对象,负责把有关状态存入到相应的观察者中
 */
public class WeatherConcreteSubject extends WeatherSubject {

	
	/*获取天气状态*/
	private String weatherContent;

	public String getWeatherContent() {
		return weatherContent;
	}

	/*通知所有的订阅的人*/
	public void setWeatherContent(String weatherContent) {
		this.weatherContent = weatherContent;
		this.notifyObserver();
	}
	
}

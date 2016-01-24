package com.forest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.forest.event.NewsOrderEvent;

@Service
public class EventDispatcherService {
	
	
	
	@Autowired
	private ApplicationEventPublisher 			publisher;
	
	// @Asynchronious
	public void publish(NewsOrderEvent event){
		publisher.publishEvent(event);
	}
	
	
}

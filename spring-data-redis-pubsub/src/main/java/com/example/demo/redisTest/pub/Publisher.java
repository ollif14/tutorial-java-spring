package com.example.demo.redisTest.pub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;


@Service
public class Publisher implements MessagePublisher {
	
	
	  @Autowired
	  private RedisTemplate redisTemplate;
	  @Autowired
	  private final ChannelTopic topic;

	  @Autowired 
	  public Publisher(RedisTemplate redisTemplate, ChannelTopic topic) {
	        this.redisTemplate = redisTemplate;
	        this.topic = topic;
	  }

	  @Override
	  public void publish(String message) {
	        redisTemplate.convertAndSend(topic.getTopic(), message.toString());
	  }
}

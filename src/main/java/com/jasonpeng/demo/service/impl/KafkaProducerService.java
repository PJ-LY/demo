package com.jasonpeng.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AdminClient adminClient;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, AdminClient adminClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.adminClient = adminClient;
    }

    /**
     * 创建主题
     * @param topicName 主题名称
     * @param numPartitions 分区数
     * @param replicationFactor 副本数
     */
    public void createTopic(String topicName, int numPartitions, short replicationFactor){
        NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);
        try {
            adminClient.createTopics(Collections.singleton(newTopic)).all().get();
            log.info("Topic {} created successfully", topicName);
        }catch (InterruptedException | ExecutionException e){
            if (e.getCause() instanceof TopicExistsException){
                log.info("Topic {} already exists", topicName);
            }else {
                log.error("Error creating topic {}", topicName, e);
            }
        }
    }

    /**
     * 删除主题
     * @param topicName 主题名称
     */
    public void deleteTopic(String topicName){
        try {
            adminClient.deleteTopics(Collections.singleton(topicName)).all().get();
            log.info("Topic {} deleted successfully", topicName);
        }catch (InterruptedException | ExecutionException e){
            log.error("Error deleting topic {}", topicName, e);
        }
    }

    /**
     * 获取主题列表
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void getTopicList() throws ExecutionException, InterruptedException {
        ListTopicsResult topics = adminClient.listTopics();
        log.info("topic list: {}", topics.listings().get());
    }

    /**
     * 发送消息
     * @param topic 主题
     * @param message 消息
     */
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        log.info("topic: {}, send message: {}", topic, message);
    }
}

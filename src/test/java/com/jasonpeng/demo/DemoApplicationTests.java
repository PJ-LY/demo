package com.jasonpeng.demo;

import com.jasonpeng.demo.service.impl.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Test
    void redisTest() {
    }

    @Test
    void kafkaTest() throws ExecutionException, InterruptedException {
        //kafka版本:2.8.2 windows删除kafka主题大坑:删除主题会导致本地kafka停止,并且删除主题后,kafka无法启动
//        解决:需要删除配置的日志目录:tmp目录下的所有文件,然后重新启动zookeeper和kafka
//        kafkaProducerService.deleteTopic("my-topic");

//        kafkaProducerService.getTopicList();
//        kafkaProducerService.createTopic("test-topic", 1, (short) 1);
//        kafkaProducerService.getTopicList();
        kafkaProducerService.sendMessage("my-topic", "hello world");
    }

}

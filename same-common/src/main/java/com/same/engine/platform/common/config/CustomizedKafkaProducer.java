package com.same.engine.platform.common.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CustomizedKafkaProducer {
    private final ConcurrentHashMap<String, KafkaProducer<String, String>> producerMap = new ConcurrentHashMap<>();

    public CustomizedKafkaProducer() {
    }

    public static CustomizedKafkaProducer GetInstance() {
        return Holder.INSTANCE;
    }

    private KafkaProducer<String, String> initProducer(String bootstrapServers, String clusterName) {
        String uniqKey = clusterName + "@" + bootstrapServers;
        KafkaProducer<String, String> producer = this.producerMap.get(uniqKey);
        if (producer == null) {
            synchronized (this) {
                producer = this.producerMap.get(uniqKey);
                if (producer == null) {

                    Map<String, Object> props = new HashMap<>();
                    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//                    props.put(ProducerConfig.CLUSTER_NAME_CONFIG, clusterName);
                    props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
                    props.put(ProducerConfig.RETRIES_CONFIG, 10);
                    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 32768);
                    props.put(ProducerConfig.LINGER_MS_CONFIG, 100);
                    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
                    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
                    props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, HuatiKafkaPartitioner.class.getName());

                    log.info("initProducer, kafka Properties = {}", JSON.toJSONString(props));
                    log.info("initProducer, uniqKey = {}, producerMap={}", uniqKey, JSON.toJSONString(producerMap));

                    producer = new KafkaProducer<String, String>(props);
                    this.producerMap.put(uniqKey, producer);
                }
            }
        }
        return producer;
    }

    /**
     * @param sync 是否同步发送
     * @param message
     * @param key
     * @param topic
     * @param bootstrapServers
     */
    public void produce(Boolean sync, String message, String key, String topic, String bootstrapServers, String clusterName) {
        KafkaProducer<String, String> producer = initProducer(bootstrapServers, clusterName);
        if (sync) {
            try {
                producer.send(new ProducerRecord<String, String>(topic, key, message)).get();
            } catch (Exception e) {
                log.error("KafkaProducer error", e);
            }
        } else {
            producer.send(new ProducerRecord<String, String>(topic, key, message));
        }
        //必须写下面这句,相当于发送
        producer.flush();
    }

    private static class Holder {
        static final CustomizedKafkaProducer INSTANCE = new CustomizedKafkaProducer();
    }
}

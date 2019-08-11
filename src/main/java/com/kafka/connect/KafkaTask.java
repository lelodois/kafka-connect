package com.kafka.connect;

import com.kafka.common.VersionUtil;
import com.kafka.model.Issue;
import com.kafka.model.User;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;

import static com.kafka.common.Schemas.*;


public class KafkaTask extends SourceTask {
    private static final Logger log = LoggerFactory.getLogger(KafkaTask.class);
    public KafkaConnectorConfig config;

    @Override
    public String version() {
        return VersionUtil.getVersion();
    }

    @Override
    public void start(Map<String, String> map) {
        config = new KafkaConnectorConfig(map);
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        final ArrayList<SourceRecord> records = new ArrayList<>();
        records.add(generateSourceRecord(Issue.newRandom()));
        log.info(String.format("Fetched %s record(s)", records.size()));
        return records;
    }

    private SourceRecord generateSourceRecord(Issue issue) {
        return new SourceRecord(
                sourcePartition(),
                sourceOffset(),
                config.getTopic(),
                KEY_SCHEMA,
                buildRecordKey(issue),
                VALUE_SCHEMA,
                buildRecordValue(issue));
    }

    @Override
    public void stop() {
    }

    private Map<String, String> sourcePartition() {
        Map<String, String> map = new HashMap<>();
        map.put("database", "transfer");
        return map;
    }

    private Map<String, String> sourceOffset() {
        Map<String, String> map = new HashMap<>();
        map.put("dateReaded", Instant.now().toString());
        return map;
    }

    private Struct buildRecordKey(Issue issue){
        return new Struct(KEY_SCHEMA)
                .put(ID, issue.getId());
    }

    public Struct buildRecordValue(Issue issue){
        Struct valueStruct = new Struct(VALUE_SCHEMA)
                .put(TITLE_FIELD, issue.getTitle())
                .put(CREATED_AT_FIELD, Date.from(issue.getCreated()))
                .put(NUMBER_FIELD, issue.getNumber())
                .put(STATE_FIELD, issue.getState());

        User user = issue.getUser();
        Struct userStruct = new Struct(USER_SCHEMA)
                .put(USER_ID_FIELD, user.getId())
                .put(USER_LOGIN_FIELD, user.getLogin());
        valueStruct.put(USER_FIELD, userStruct);
        return valueStruct;
    }

}
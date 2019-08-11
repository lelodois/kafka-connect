package com.kafka.common;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Timestamp;

public class Schemas {

    public static final String ID = "owner";
    public static final String CREATED_AT_FIELD = "created_at";
    public static final String NUMBER_FIELD = "number";
    public static final String TITLE_FIELD = "title";
    public static final String STATE_FIELD = "state";

    // User fields
    public static final String USER_FIELD = "user";
    public static final String USER_ID_FIELD = "id";
    public static final String USER_LOGIN_FIELD = "login";

    // Schema names
    public static final String SCHEMA_KEY = "com.simplesteph.kafka.connect.github.IssueKey";
    public static final String SCHEMA_VALUE_ISSUE = "com.simplesteph.kafka.connect.github.IssueValue";
    public static final String SCHEMA_VALUE_USER = "com.simplesteph.kafka.connect.github.UserValue";

    // Key Schema
    public static final Schema KEY_SCHEMA = SchemaBuilder.struct().name(SCHEMA_KEY)
            .version(1)
            .field(ID, Schema.INT32_SCHEMA)
            .build();

    // Value Schema
    public static final Schema USER_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_USER)
            .version(1)
            .field(USER_ID_FIELD, Schema.INT32_SCHEMA)
            .field(USER_LOGIN_FIELD, Schema.STRING_SCHEMA)
            .build();

    public static final Schema VALUE_SCHEMA =
            SchemaBuilder.struct().name(SCHEMA_VALUE_ISSUE)
            .version(2)
            .field(TITLE_FIELD, Schema.STRING_SCHEMA)
            .field(CREATED_AT_FIELD, Timestamp.SCHEMA)
            .field(NUMBER_FIELD, Schema.INT32_SCHEMA)
            .field(STATE_FIELD, Schema.STRING_SCHEMA)
            .field(USER_FIELD, USER_SCHEMA)
            .build();
}

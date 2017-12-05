package com.elastic.test.services;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ElasticClient {

    private ElasticClient() {

    }

    private static ElasticClient instance = null;

    @Value("${elasticsearch.host}")
    private String elasticHost;
    @Value("${elasticsearch.port}")
    private String elasticPort;
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    public static ElasticClient getInstance() {
        if (instance == null)
            instance = new ElasticClient();

        return instance;
    }

    public Client getConnection() {
        Settings settings = Settings.settingsBuilder().put("node.cliente").build();
        return null;
    }


}

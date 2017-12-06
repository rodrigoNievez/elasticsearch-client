package com.elastic.test.services;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class ElasticClient {

    private ElasticClient() {

    }

    private static ElasticClient instance = null;

    @Value("${elasticsearch.host}")
    private String elasticHost;
    @Value("${elasticsearch.port}")
    private int elasticPort;
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    public static ElasticClient getInstance() {
        if (instance == null)
            instance = new ElasticClient();

        return instance;
    }

    public Client getConnection() {
        try {
            Settings settings = Settings.settingsBuilder().put("node.client", true)
                    .put("client.transport.sniff", true)
                    .put("cluster.name", clusterName).build();
            Client client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticHost), elasticPort));
            return client;
        } catch (UnknownHostException e) {
            System.out.printf("Error al conectar al elasticsearch, causa: " + e.getMessage());
        }

        return null;
    }


}

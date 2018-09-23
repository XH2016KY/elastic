package com.elastic.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MyConfig {

    @Bean
    public TransportClient  client() throws UnknownHostException {
        TransportAddress node = new TransportAddress(
                InetAddress.getByName("192.168.200.154"),
                9300
        );
        TransportAddress node1 = new TransportAddress(
                InetAddress.getByName("192.168.200.154"),
                9301
        );
      TransportAddress node2 = new TransportAddress(
                InetAddress.getByName("192.168.200.154"),
                9302
        );
        Settings settings = Settings.builder()
                .put("cluster.name","GGG").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        client.addTransportAddress(node1);
        client.addTransportAddress(node2);
        return client;
    }
}

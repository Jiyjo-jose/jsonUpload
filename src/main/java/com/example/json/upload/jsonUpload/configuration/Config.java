package com.example.json.upload.jsonUpload.configuration;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.client.RestClient;


@Configuration
public class Config {
    @Value("${elasticsearch.host:localhost}")
    public String host;

    @Value("${elasticsearch.port:9200}")
    public int port;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(host, port)));
    }
}

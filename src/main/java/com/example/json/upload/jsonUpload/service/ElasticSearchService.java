package com.example.json.upload.jsonUpload.service;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class ElasticSearchService {
    private final RestHighLevelClient client;

    public void upload(String index, MultipartFile multipartFile) throws IOException {
        String jsonString = new String(multipartFile.getBytes());
        IndexRequest request = new IndexRequest(index);
        request.source(jsonString, XContentType.JSON);

        client.index(request, RequestOptions.DEFAULT);
    }
}

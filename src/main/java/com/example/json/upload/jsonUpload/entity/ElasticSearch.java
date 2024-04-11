package com.example.json.upload.jsonUpload.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "json")
public class ElasticSearch {
    private Long id;
}

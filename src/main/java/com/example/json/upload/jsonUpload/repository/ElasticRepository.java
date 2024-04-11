package com.example.json.upload.jsonUpload.repository;
import com.example.json.upload.jsonUpload.entity.ElasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticRepository extends ElasticsearchRepository<ElasticSearch, Long> {
}

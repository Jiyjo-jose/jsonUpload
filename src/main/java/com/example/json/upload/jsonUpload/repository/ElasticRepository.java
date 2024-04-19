package com.example.json.upload.jsonUpload.repository;
import com.example.json.upload.jsonUpload.entity.ElasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticRepository extends ElasticsearchRepository<ElasticSearch, Long> {
//    @Query("{\"bool\":{\"must\":[{\"match\":{\"language\":\"?0\"}},{\"match\":{\"experienceLevel\":\"?1\"}},{\"match\":{\"role\":\"?2\"}}]}}")
//    Page<File> search(@Param("language") String language, @Param("experienceLevel") String experienceLevel, @Param("role") String role, Pageable pageable);

}

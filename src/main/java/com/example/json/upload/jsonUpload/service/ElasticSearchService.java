package com.example.json.upload.jsonUpload.service;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;

import org.elasticsearch.action.search.SearchRequest;

import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RequestOptions;

import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.index.query.BoolQueryBuilder;

import org.elasticsearch.index.query.QueryBuilders;

import org.elasticsearch.search.SearchHit;

import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.elasticsearch.search.sort.FieldSortBuilder;

import org.elasticsearch.search.sort.SortOrder;

import org.elasticsearch.xcontent.XContentType;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import java.util.Map;


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
    public List<?> searchResume(String indexName, String language, String fromExperienceLevel, String role, String orderBy, String sortOrder, Integer from, Integer size) throws IOException {

        SearchRequest searchRequest = new SearchRequest(indexName);

        String orderByField;

        switch (orderBy) {

            case "fromExperienceLevel":

                orderByField = "skillsAndTechnologies.totalYearsOfExperience";

                break;

            case "language":

                orderByField = "skillsAndTechnologies.programmingLanguages";

                break;

            case "role":

                orderByField = "skillsAndTechnologies.role";

                break;

            default:

                orderByField = null;

        }

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()

                .must(QueryBuilders.matchQuery("skillsAndTechnologies.programmingLanguages", language))                        .must(QueryBuilders.rangeQuery("skillsAndTechnologies.totalYearsOfExperience").gte(fromExperienceLevel))

                .must(QueryBuilders.matchQuery("skillsAndTechnologies.role", role));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()

                .query(boolQueryBuilder)

                .from(from)

                .size(size);

        if (orderBy != null) {

            sourceBuilder.sort(new FieldSortBuilder(orderByField).order(SortOrder.fromString(sortOrder)));

        }

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        List<Map> result = new ArrayList<>();

        for (SearchHit hit : searchResponse.getHits().getHits()) {

            result.add(hit.getSourceAsMap());

        }

        return result;

    }

}

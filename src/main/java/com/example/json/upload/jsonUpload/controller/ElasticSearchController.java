package com.example.json.upload.jsonUpload.controller;
import com.example.json.upload.jsonUpload.service.ElasticSearchService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ElasticSearchController {
    private final ElasticSearchService elasticSearchService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws Exception{
        elasticSearchService.upload("json", multipartFile);
        return "File upload successfully";
    }
    @GetMapping("/search")

    public ResponseEntity<List<?>> search(

            @RequestParam("indexName") String indexName,

            @RequestParam(value = "language", required = false) String language,

            @RequestParam(value = "fromExperienceLevel", required = false) String fromExperienceLevel,

            @RequestParam(value = "role", required = false) String role,

            @RequestParam(value = "orderBy", required = false) String orderBy,

            @RequestParam(value = "sortOrder", required = false) String sortOrder,

            @RequestParam(value = "from", required = false) Integer from,

            @RequestParam(value = "size", required = false) Integer size

    ) throws IOException {

        List<?> response = elasticSearchService.searchResume(indexName, language, fromExperienceLevel, role, orderBy, sortOrder, from, size);

        return ResponseEntity.ok(response);

    }


}

package com.example.json.upload.jsonUpload.controller;
import com.example.json.upload.jsonUpload.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}

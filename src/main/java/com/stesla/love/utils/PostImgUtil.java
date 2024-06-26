package com.stesla.love.utils;


import com.alibaba.fastjson2.JSON;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class PostImgUtil {
    public static final String uploadUrl = "https://sm.ms/api/v2/upload";
    public static final String deleteUrl = "https://sm.ms/delete/";
    public static final String token = "0G4mRLYFHPiIIgWBwcGD6eGFUOV7ntox";

    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            file.transferTo(convFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    public static Map<String,String> uploadImg(File img) {

        HttpResponse<String> response = Unirest.post(uploadUrl)
                //这里*****是指上一步获取的token
                .header("Authorization", token)
                .field("smfile", img)
                .asString();

        // 解析data字段
        Map<String, Object> map1 = JSON.parseObject(response.getBody(), Map.class);
        Map<String, Object> data = (Map<String, Object>) map1.get("data");
        String s = (String) data.get("url");
        String s1 = (String) data.get("hash");
        Map<String,String> result = new HashMap<>();
        result.put("url", s);
        result.put("hash", s1);
        return result;
    }
    public static Map<String, Object> deleteImg(String hash) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(deleteUrl + hash, request, String.class);
        return JSON.parseObject(response.getBody(), Map.class);
    }
}

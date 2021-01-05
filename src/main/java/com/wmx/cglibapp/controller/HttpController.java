package com.wmx.cglibapp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2021/1/5 20:07
 */
@RestController
public class HttpController {

    @GetMapping("v1/http/get")
    public Map<String, Object> httpGet(@RequestParam String id, Boolean isEnd, Float salary) {
        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("id", id);
        dataMap.put("isEnd", isEnd);
        dataMap.put("salary", salary);
        System.out.println(dataMap);
        return dataMap;
    }

    @PostMapping("v1/http/post")
    public Map<String, Object> httpPost(@RequestParam String id, Boolean isEnd, Float salary) {
        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("id_", id);
        dataMap.put("isEnd_", isEnd);
        dataMap.put("salary_", salary);
        System.out.println(dataMap);
        return dataMap;
    }


    @PostMapping("v1/http/requestBody")
    public Map<String, Object> httpPost2(@RequestParam String id, @RequestBody List<Map<String, Object>> dataList) {
        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("id", id);
        dataMap.put("list", dataList);
        System.out.println(dataMap);
        return dataMap;
    }

    @PutMapping("v1/http/put")
    public Map<String, Object> httpPut(@RequestParam String id, Boolean isEnd, Float salary) {
        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("id_", id);
        dataMap.put("salary_", salary);
        dataMap.put("isEnd_", isEnd);
        System.out.println(dataMap);
        return dataMap;
    }

    @DeleteMapping("v1/http/delete")
    public Map<String, Object> httDelete(@RequestBody List<String> ids) {
        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("ids", ids);
        System.out.println(dataMap);
        return dataMap;
    }


}

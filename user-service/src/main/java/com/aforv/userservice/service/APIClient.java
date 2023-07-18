package com.aforv.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.aforv.userservice.dto.DepartmentDto;

@FeignClient(value = "DEPARTMENT-SERVICE", url = "http://DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping(value = "/api/departments/{id}")
    DepartmentDto getDepartmentById(@PathVariable("id") Long departmentId);
}
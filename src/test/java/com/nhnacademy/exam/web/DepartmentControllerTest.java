package com.nhnacademy.exam.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.department.dto.DepartmentRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 16/05/2023
 */

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("부서-등록")
    void register() throws Exception {
        DepartmentRequest departmentRequest = new DepartmentRequest();
        ReflectionTestUtils.setField(departmentRequest,"deptId","CS0001");
        ReflectionTestUtils.setField(departmentRequest,"deptName","고객서비스1팀");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/department")
                .header("X-USER-ID","nhnacademy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(departmentRequest));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(departmentRequest.getDeptId()))
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("부서-조회")
    void getDepartment() throws Exception {
        register();
        DepartmentRequest departmentRequest = new DepartmentRequest();
        ReflectionTestUtils.setField(departmentRequest,"deptId","CS0001");
        ReflectionTestUtils.setField(departmentRequest,"deptName","고객서비스1팀");


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/department/{id}",departmentRequest.getDeptId())
                .header("X-USER-ID","nhnacademy")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentRequest.getDeptId()))
                .andExpect(jsonPath("$.name").value(departmentRequest.getDeptName()))
                .andDo(print());
    }


    @Test
    @Order(3)
    @DisplayName("부서-수정")
    void updateDepartment() throws Exception {
        register();
        DepartmentRequest departmentRequest = new DepartmentRequest();
        ReflectionTestUtils.setField(departmentRequest,"deptId","CS0001");
        ReflectionTestUtils.setField(departmentRequest,"deptName","CS1팀");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/department/{id}",departmentRequest.getDeptId())
                .header("X-USER-ID","nhnacademy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(departmentRequest));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(4)
    @DisplayName("부서-삭제")
    void deleteDepartment() throws Exception {
        register();
        String deptId="CS0001";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/department/{id}",deptId)
                .header("X-USER-ID","nhnacademy")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @Order(5)
    @DisplayName("예외 - 존재하지 않는 부서")
    void notfound() throws Exception {
        String deptId="CS0002";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/department/{id}",deptId)
                .header("X-USER-ID","nhnacademy")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
              //  .andExpect(jsonPath("$.title").value("department not found : " + deptId))
                .andDo(print());
    }
}
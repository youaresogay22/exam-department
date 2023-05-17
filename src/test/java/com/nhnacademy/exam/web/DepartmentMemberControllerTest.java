package com.nhnacademy.exam.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.stream.Stream;
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
class DepartmentMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final String X_USER_ID="nhnacademy";

    @DisplayName("TXT-부서/직원 리스트:L1001")
    @MethodSource("deptMemberListParams")
    @ParameterizedTest(name = "departmentId:{0}, size:{1}")
    void deptMemberList(String id, int size) throws Exception {
        RequestBuilder requestBuilder =  MockMvcRequestBuilders
                .get("/department-members?departmentIds={id}",id)
                .header("Accept", MediaType.APPLICATION_JSON)
                .header("X-USER-ID",X_USER_ID);

        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$.length()").value(size))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private static Stream<Arguments> deptMemberListParams(){
        return Stream.of(
                Arguments.of("L1001",5),
                Arguments.of("L1002",4),
                Arguments.of("L1003",3),
                Arguments.of("L1004",2),
                Arguments.of("L1005",2),
                Arguments.of("A9001",2),
                Arguments.of("A9002",2),
                Arguments.of("A9003",2),
                Arguments.of("A9004",2),
                Arguments.of("A9005",2),
                Arguments.of("CS001",3),
                Arguments.of("CS002",3),
                Arguments.of("CS003",2),
                Arguments.of("CS004",1),
                Arguments.of("CS005",1)
        );
    }

    @Test
    @DisplayName("Missing Parameter : departmentIds")
    void missingParameter() throws Exception {
        RequestBuilder requestBuilder =  MockMvcRequestBuilders
                .get("/department-members?departmentIds")
                .header("Accept", MediaType.APPLICATION_JSON)
                .header("X-USER-ID",X_USER_ID);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }


    @Test
    @DisplayName("Accpet:application/csv")
    void notAllowdCsv() throws Exception {
        RequestBuilder requestBuilder =  MockMvcRequestBuilders
                .get("/department-members?departmentIds={id}","L1001")
                .header("Accept", "application/csv")
                .header("X-USER-ID",X_USER_ID);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("Unauthorized, X-USER-ID:marco")
    void unauthorized1() throws Exception {
        RequestBuilder requestBuilder =  MockMvcRequestBuilders
                .get("/department-members?departmentIds={id}","L1001")
                .header("Accept", "application/json")
                .header("X-USER-ID","marco");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("Unauthorized, X-USER-ID: empty")
    void unauthorized2() throws Exception {
        RequestBuilder requestBuilder =  MockMvcRequestBuilders
                .get("/department-members?departmentIds={id}","L1001")
                .header("Accept", "application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("Unauthorized, X-USER-ID: blank")
    void unauthorized3() throws Exception {
        RequestBuilder requestBuilder =  MockMvcRequestBuilders
                .get("/department-members?departmentIds={id}","L1001")
                .header("Accept", "application/json")
                .header("X-USER-ID","");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("Content negotiation-xml")
    void responseXml() throws Exception {
        RequestBuilder requestBuilder =  MockMvcRequestBuilders
                .get("/department-members?departmentIds={id}&format=","L1001","xml")
                .header("Accept", "application/xml")
                .header("X-USER-ID","nhnacademy");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andDo(print());
    }

}
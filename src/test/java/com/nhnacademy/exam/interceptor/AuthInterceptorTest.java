package com.nhnacademy.exam.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.advice.GlobalExceptionHandler;
import com.nhnacademy.exam.controller.DepartmentController;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;

@ActiveProfiles("dev")
class AuthInterceptorTest {
    MockMvc mockMvc;
    DepartmentService departmentService;
    ObjectMapper objectMapper = new ObjectMapper();
    Department department;

    @BeforeEach
    void setUp() {
        departmentService = mock(DepartmentService.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new DepartmentController(departmentService))
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();

        department = new Department();
        department.setId("test");
        department.setName("name");
        department.setMember(null);
    }

    @Test
    void preHandle() throws Exception {


//        assertThatThrownBy(() -> mockMvc.perform(
//                delete("/departments/test")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//
//        )).hasCause(new UserAuthenticationFailedException());
    }
}
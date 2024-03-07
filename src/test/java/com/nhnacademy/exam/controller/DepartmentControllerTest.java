package com.nhnacademy.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.advice.GlobalExceptionHandler;
import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.domain.DepartmentNameDTO;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.exception.NoSuchDepartmentIdException;
import com.nhnacademy.exam.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
class DepartmentControllerTest {
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
    void createDepartment() throws Exception {
        DepartmentDTO dto = objectMapper.convertValue(department, DepartmentDTO.class);

        when(departmentService.exists(anyString())).thenReturn(false);

        mockMvc.perform(
                        post("/departments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-USER-ID", "nhnacademy")
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("test"))
                .andReturn();
        verify(departmentService, times(1)).createDepartment(any());
    }

    @Test
    void createDepartment_noIdException() {
        DepartmentDTO dto = objectMapper.convertValue(department, DepartmentDTO.class);

        when(departmentService.exists(anyString())).thenReturn(true);

        assertThatThrownBy(() -> mockMvc.perform(
                        post("/departments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-USER-ID", "nhnacademy")
                                .content(objectMapper.writeValueAsString(dto))
                )
        ).hasCause(new DuplicateDepartmentIdException());
    }

    @Test
    void findDepartment() throws Exception {
        DepartmentDTO dto = objectMapper.convertValue(department, DepartmentDTO.class);

        when(departmentService.findDepartment(any())).thenReturn(dto);
        when(departmentService.exists(anyString())).thenReturn(true);

        mockMvc.perform(
                        get("/departments/test")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-USER-ID", "nhnacademy")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("test"))
                .andExpect(jsonPath("$.name").value("name"))
                .andReturn();

        verify(departmentService, times(1)).findDepartment(anyString());
    }

    @Test
    void findDepartment_noIdException() {
        when(departmentService.exists(anyString())).thenReturn(false);

        assertThatThrownBy(() -> mockMvc.perform(
                        get("/departments/test")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-USER-ID", "nhnacademy")
                )
        ).hasCause(new NoSuchDepartmentIdException());
    }

    @Test
    void updateDepartment() throws Exception {
        Department department2 = new Department();
        department2.setName("name2");
        DepartmentNameDTO dto = objectMapper.convertValue(department2, DepartmentNameDTO.class);

        when(departmentService.exists(anyString())).thenReturn(true);

        mockMvc.perform(
                        put("/departments/test")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-USER-ID", "nhnacademy")
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        verify(departmentService, times(1)).updateDepartment(any());
    }

    @Test
    void updateDepartment_noIdException() {
        Department department2 = new Department();
        department2.setName("name2");
        DepartmentNameDTO dto = objectMapper.convertValue(department2, DepartmentNameDTO.class);

        when(departmentService.exists(anyString())).thenReturn(false);

        assertThatThrownBy(() -> mockMvc.perform(
                        put("/departments/test")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-USER-ID", "nhnacademy")
                                .content(objectMapper.writeValueAsString(dto))
                )
        ).hasCause(new NoSuchDepartmentIdException());
    }

    @Test
    void deleteDepartment() throws Exception {
        when(departmentService.exists(anyString())).thenReturn(true);

        mockMvc.perform(
                        delete("/departments/test")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-USER-ID", "nhnacademy")
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        verify(departmentService, times(1)).deleteDepartment(anyString());
    }

    @Test
    void deleteDepartment_noIdException() {
        when(departmentService.exists(anyString())).thenReturn(false);

        assertThatThrownBy(() -> mockMvc.perform(
                delete("/departments/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID", "nhnacademy")
        )).hasCause(new NoSuchDepartmentIdException());

    }

}
package com.theruzil.image_catalog.integration.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createFile() {

    }

    @Test
    public void getOneFile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/file/7")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getFiles() {

    }

    @Test
    public void updateFile() {

    }

    @Test
    public void deleteFile() {

    }
}

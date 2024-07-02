package com.ponto;

import com.ponto.controller.UserController;
import com.ponto.model.UserEntity;
import com.ponto.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(UserController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsuarios() throws Exception {
        UserEntity usuario = new UserEntity();
        user.setId(1L);
        user.setNome("Teste");
        user.setEmail("teste@teste.com");

        Mockito.when(userService.findAll()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Teste"))
                .andExpect(jsonPath("$[0].email").value("teste@teste.com"))
                .andDo(print());
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setNome("Teste");
        user.setEmail("teste@teste.com");

        Mockito.when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Teste"))
                .andExpect(jsonPath("$.email").value("teste@teste.com"))
                .andDo(print());
    }
}

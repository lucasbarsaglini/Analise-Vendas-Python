package com.ponto;

import com.ponto.model.UserEntity;
import com.ponto.repository.UserRepository;
import com.ponto.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testFindById() {
        UserEntity usuario = new UserEntity();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<UserEntity> encontrado = userService.findById(1L);
        assertTrue(encontrado.isPresent());
        assertEquals("Teste", encontrado.get().getNome());
    }
}

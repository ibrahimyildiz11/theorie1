package be.vdab.taken.services;

import be.vdab.taken.repositories.KostRepository;
import be.vdab.taken.repositories.KostRepositoryStub;
import be.vdab.taken.repositories.OpbrengstRepository;
import be.vdab.taken.repositories.OpbrengstRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) class WinstServiceTest {
    @Mock
    private OpbrengstRepository opbrengstRepository;
    @Mock
    private KostRepository kostRepository;
    private WinstService winstService;
    @BeforeEach
    void beforeEach() {
        winstService = new WinstService(opbrengstRepository, kostRepository);
    }

    @Test
    void winstIsOpbrengstMinKost() {
        when(opbrengstRepository.findTotaleOpbrengst()).thenReturn(BigDecimal.valueOf(200));
        when(kostRepository.findTotaleKost()).thenReturn(BigDecimal.valueOf(169));

        assertThat(winstService.getWinst()).isEqualByComparingTo("31");

        verify(opbrengstRepository).findTotaleOpbrengst();
        verify(kostRepository).findTotaleKost();
    }
}
package com.example.subscriberbc.service;

import com.example.subscriberbc.dto.SubscriberDto;
import com.example.subscriberbc.mapper.SubscriberMapper;
import com.example.subscriberbc.model.Subscriber;
import com.example.subscriberbc.repository.SubscriberRepository;
import com.example.subscriberbc.service.impl.SubscriberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
public class SubscriberServiceTest {

    @InjectMocks
    SubscriberServiceImpl subscriberService;
    @Mock
    SubscriberRepository subscriberRepository;
    @Mock
    SubscriberMapper subscriberMapper;

    @Test
    @DisplayName("Cette méthode permet de créer un abonné.")
    void createSubscriber() {
        SubscriberDto dto = new SubscriberDto("firstName","lastName","mail@google.fr","1234567891",true);
        when(subscriberRepository.save(any())).thenReturn(
                new Subscriber(1L,"firstName","lastName","mail@google.fr","1234567891",true));
        when(subscriberMapper.dtoToSubscriber(any())).thenReturn(
                new Subscriber());
        assertNotNull(subscriberService.createSubscriber(dto));
        assertEquals(1,subscriberService.createSubscriber(dto));
    }

    @Test
    @DisplayName("Cette méthode permet de rechercher un abonné par plusieurs critéres.")
    void getSubscribersWithCriteria() {
        SubscriberDto dto = new SubscriberDto("firstName","lastName","mail@google.fr","1234567891",true);
        when(subscriberMapper.subscribersToDtos(any())).thenReturn(
                Arrays.asList((dto)));
        assertNotNull(subscriberService.getSubscribersWithCriteria(dto));
        assertEquals(1,subscriberService.getSubscribersWithCriteria(dto).size());
    }

    @Test
    @DisplayName("Cette méthode permet de résilier un abonné.")
    void cancelSubscriber() {
        when(subscriberRepository.findById(any())).thenReturn(
                Optional.of(new Subscriber(1L,"firstName","lastName","mail@google.fr","1234567891",true)));
        when(subscriberRepository.save(any())).thenReturn(
                new Subscriber(1L,"firstName","lastName","mail@google.fr","1234567891",true));
        assertTrue(subscriberService.cancelSubscriber(1L));
    }

    @Test
    @DisplayName("Cette méthode permet de mettre à jour un abonné.")
    void updateSubscriber() {
        SubscriberDto dto = new SubscriberDto("firstName","lastName","mail@google.fr","1234567891",true);
        when(subscriberRepository.findById(any())).thenReturn(
                Optional.of(new Subscriber(1L,"firstName","lastName","mail@google.fr","1234567891",true)));
        when(subscriberRepository.save(any())).thenReturn(
                new Subscriber(1L,"firstName","lastName","mail@google.fr","1234567891",true));
        when(subscriberMapper.subscriberToDto(any())).thenReturn(dto);

        assertNotNull(subscriberService.updateSubscriber(1L,dto));
        assertEquals("mail@google.fr",subscriberService.updateSubscriber(1L,dto).getMail());
    }
}

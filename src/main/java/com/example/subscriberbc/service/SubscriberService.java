package com.example.subscriberbc.service;

import com.example.subscriberbc.dto.SubscriberDto;

import java.util.List;

public interface SubscriberService {

    Long createSubscriber(SubscriberDto dto);
    List<SubscriberDto> getSubscribersWithCriteria(SubscriberDto dto);
    boolean cancelSubscriber(Long id);
    SubscriberDto updateSubscriber(Long id, SubscriberDto dto);
}

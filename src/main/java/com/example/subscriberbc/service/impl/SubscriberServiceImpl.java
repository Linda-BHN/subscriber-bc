package com.example.subscriberbc.service.impl;

import com.example.subscriberbc.dto.SearchCriteriaDto;
import com.example.subscriberbc.dto.SubscriberDto;
import com.example.subscriberbc.mapper.SubscriberMapper;
import com.example.subscriberbc.model.Subscriber;
import com.example.subscriberbc.repository.SubscriberRepository;
import com.example.subscriberbc.repository.specification.SubscriberSpecification;
import com.example.subscriberbc.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;
    @Autowired
    SubscriberMapper subscriberMapper;

    @Override
    public Long createSubscriber(SubscriberDto dto){
        if(subscriberRepository.existsByFirstNameAndLastNameAndMailAndPhone(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getMail(),
                dto.getPhone()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "L'abonné %s %s existe déjà.".formatted(dto.getFirstName(),dto.getLastName()));

        return subscriberRepository
                .save(subscriberMapper.dtoToSubscriber(dto)).getId();
    }
    @Override
    public List<SubscriberDto> getSubscribersWithCriteria(SubscriberDto dto){
        SubscriberSpecification specFName =
                new SubscriberSpecification(new SearchCriteriaDto("firstName", "=", dto.getFirstName()));
        SubscriberSpecification specLName =
                new SubscriberSpecification(new SearchCriteriaDto("lastName", "=", dto.getLastName()));
        SubscriberSpecification specMail =
                new SubscriberSpecification(new SearchCriteriaDto("mail", "=", dto.getMail()));
        SubscriberSpecification specPhone =
                new SubscriberSpecification(new SearchCriteriaDto("phone", "=", dto.getPhone()));
        SubscriberSpecification specActive =
                new SubscriberSpecification(new SearchCriteriaDto("active", "=", dto.isActive()));
        List<Subscriber> results = subscriberRepository.findAll(Specification.where(specFName)
                .and(specLName)
                .and(specMail)
                .and(specPhone)
                .and(specActive));
        return subscriberMapper.subscribersToDtos(results);
    }
    @Override
    public boolean cancelSubscriber(Long id) {
        Subscriber subscriber = getSubscriberById(id);
        subscriberRepository
                .save(subscriber.setActive(false));
        return true;
    }

    @Override
    public SubscriberDto updateSubscriber(Long id, SubscriberDto dto) {
        Subscriber subscriber = getSubscriberById(id);
        subscriber = subscriberRepository
                .save(subscriberMapper.dtoIntoSubscriber(subscriber,dto));
        return subscriberMapper.subscriberToDto(subscriber);
    }

    private Subscriber getSubscriberById(Long id){
        return subscriberRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "L'abonné avec l'id %d est introuvable.".formatted(id)));
    }
}

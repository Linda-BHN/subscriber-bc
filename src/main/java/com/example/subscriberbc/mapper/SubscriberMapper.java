package com.example.subscriberbc.mapper;

import com.example.subscriberbc.dto.SubscriberDto;
import com.example.subscriberbc.model.Subscriber;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {

    Subscriber dtoToSubscriber(SubscriberDto source);
    Subscriber dtoIntoSubscriber(@MappingTarget Subscriber target,SubscriberDto source);
    SubscriberDto subscriberToDto(Subscriber source);
    List<SubscriberDto> subscribersToDtos(List<Subscriber> sources);
}

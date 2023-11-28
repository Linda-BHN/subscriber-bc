package com.example.subscriberbc.controller;

import com.example.subscriberbc.dto.SubscriberDto;
import com.example.subscriberbc.service.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribers")
@Tag(name="SubscriberController", description = "Api Subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @Operation(description = "Permet de créer un abonné.")
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody SubscriberDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(subscriberService.createSubscriber(dto));
    }

    @Operation(description = "Permet de récupérer un abonné par plusieurs critère de recherche.")
    @PostMapping(value = "/search")
    public ResponseEntity<List<SubscriberDto>> getSubscribers(@RequestBody SubscriberDto dto) {
        return ResponseEntity
                .ok(subscriberService.getSubscribersWithCriteria(dto));
    }

    @Operation(description = "Permet de résilier un abonné.")
    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<Boolean> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(subscriberService.cancelSubscriber(id));
    }

    @Operation(description = "Permet de mettre à jour les données personnelles d’un abonné.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SubscriberDto> update(@PathVariable Long id, @RequestBody SubscriberDto dto) {
        return ResponseEntity.ok(subscriberService.updateSubscriber(id,dto));
    }


}

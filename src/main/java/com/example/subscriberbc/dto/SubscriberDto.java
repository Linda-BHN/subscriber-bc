package com.example.subscriberbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberDto {

    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private boolean active;

}

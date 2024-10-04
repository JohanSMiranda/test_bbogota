package com.backend.bbogota.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private String phone;
    private String address;
    private String city;
}

package com.backend.bbogota.backend.repository;


import com.backend.bbogota.backend.model.Client;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ClientRepository {

    private static final Map<String, Client> data = new HashMap<>();

    static {
        data.put(
            "C23445322",
            new Client("Johan1",
                    "Sebastian1",
                    "Miranda1",
                    "Gualdron1",
                    "3143020128",
                    "Carrera 84$75-42",
                    "Bogotá")
        );
        data.put(
            "P23445322",
            new Client("Johan2",
                    "Sebastian2",
                    "Miranda2",
                    "Gualdron2",
                    "3143020128",
                    "Carrera 84$75-42",
                    "Bogotá")
        );
    }

    public Client findByDocument(String type, String number) {
        return data.get(type + number);
    }
}
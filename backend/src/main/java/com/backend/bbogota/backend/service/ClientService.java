package com.backend.bbogota.backend.service;

import com.backend.bbogota.backend.exception.ClientException;
import com.backend.bbogota.backend.model.Client;
import com.backend.bbogota.backend.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClient(String type, String number) {
        if (!type.equals("C") && !type.equals("P")) {
            throw new ClientException("Tipo de documento inválido", HttpStatus.BAD_REQUEST);
        }

        Client client = clientRepository.findByDocument(type, number);
        if (client == null) {
            throw new ClientException("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        return client;
    }
}
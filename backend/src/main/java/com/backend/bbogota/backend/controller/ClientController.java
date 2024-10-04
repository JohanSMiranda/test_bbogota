package com.backend.bbogota.backend.controller;

import com.backend.bbogota.backend.ResponseDTO.ClientResponseDTO;
import com.backend.bbogota.backend.exception.ClientException;
import com.backend.bbogota.backend.model.Client;
import com.backend.bbogota.backend.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<Object> getClientInfo(@RequestBody ClientResponseDTO clientResponseDTO) {
        UUID uuid = UUID.randomUUID();
        if (clientResponseDTO.getDocumentNumber() == null || clientResponseDTO.getDocumentNumber().isEmpty() ||
                clientResponseDTO.getType() == null || clientResponseDTO.getType().isEmpty()) {
            logger.error(String.format("%s - El número de documento o el tipo no pueden ser nulos o vacíos", uuid));
            return new ResponseEntity<>("El número de documento o el tipo no pueden ser nulos o vacíos", HttpStatus.BAD_REQUEST);
        }

        try {
            Client client = clientService.getClient(clientResponseDTO.getType(), clientResponseDTO.getDocumentNumber());
            logger.info(String.format("%s - Cliente encontrado: %s",uuid, client));
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ClientException ce) {
            logger.error(String.format("%s - Error encontrado: %s - documento: %s", uuid, ce.getMessage(), clientResponseDTO.getDocumentNumber()));
            return new ResponseEntity<>(ce.getMessage(), ce.getStatus());
        } catch (Exception e) {
            logger.error(String.format("%s - Error interno del servidor: %s",uuid, e.getMessage()), e);
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
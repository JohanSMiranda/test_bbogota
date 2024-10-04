package com.backend.bbogota.backend;

import com.backend.bbogota.backend.ResponseDTO.ClientResponseDTO;
import com.backend.bbogota.backend.controller.ClientController;
import com.backend.bbogota.backend.exception.ClientException;
import com.backend.bbogota.backend.model.Client;
import com.backend.bbogota.backend.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BackendApplicationTests {

	@InjectMocks
	private ClientController clientController;

	@Mock
	private ClientService clientService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetClientInfo_ValidRequest() {

		ClientResponseDTO request = new ClientResponseDTO();
		request.setType("P");
		request.setDocumentNumber("23445322");

		Client expectedClient = new Client(
				"Johan2",
				"Sebastian2",
				"Miranda2",
				"Gualdron2",
				"3143020128",
				"Carrera 84$75-42",
				"Bogotá");

		when(clientService.getClient(request.getType(), request.getDocumentNumber())).thenReturn(expectedClient);

		// Act
		ResponseEntity<Object> response = clientController.getClientInfo(request);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedClient, response.getBody());
	}

	@Test
	public void testGetClientInfo_DocumentNumberOrTypeNull() {
		ClientResponseDTO request = new ClientResponseDTO();
		request.setType(null);
		request.setDocumentNumber(null);

		ResponseEntity<Object> response = clientController.getClientInfo(request);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("El número de documento o el tipo no pueden ser nulos o vacíos", response.getBody());
	}

	@Test
	public void testGetClientInfo_ClientNotFound() {
		ClientResponseDTO request = new ClientResponseDTO();
		request.setType("P");
		request.setDocumentNumber("23445322");

		when(clientService.getClient(request.getType(), request.getDocumentNumber())).thenThrow(new ClientException("Cliente no encontrado", HttpStatus.NOT_FOUND));

		ResponseEntity<Object> response = clientController.getClientInfo(request);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Cliente no encontrado", response.getBody());
	}

	@Test
	public void testGetClientInfo_InternalServerError() {
		ClientResponseDTO request = new ClientResponseDTO();
		request.setType("C");
		request.setDocumentNumber("12345678");

		when(clientService.getClient(request.getType(), request.getDocumentNumber())).thenThrow(new RuntimeException("Error interno del servidor"));

		ResponseEntity<Object> response = clientController.getClientInfo(request);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error interno del servidor", response.getBody());
	}
}


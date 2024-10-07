# Proyecto Spring Boot

Este proyecto está desarrollado en **Java 17** y expone un servicio RESTful en el puerto **8090**.

## Endpoint

El endpoint principal de la aplicación es:

http://localhost:8090/api/client

## Método

### POST

Este endpoint acepta solicitudes POST y requiere un request body en formato JSON con la siguiente estructura:
```json
{
    "type": "C",
    "documentNumber": "23445322"
}
```

### Parámetros

- **type**: Tipo de documento (acepta "C" para cédula y "P" para pasaporte).
- **documentNumber**: Número de documento del cliente.

> Nota: Ningún otro tipo de documento está permitido. 

## Mapeo de Errores

La aplicación maneja los siguientes errores:

1. **El número de documento o el tipo no pueden ser nulos o vacíos**  
   **Código de estado**: 400 - BAD_REQUEST  
   Ocurre cuando algún dato del request body está vacío o nulo.

2. **Error interno del servidor**  
   **Código de estado**: 500 - INTERNAL_SERVER_ERROR  
   Ocurre por un error desconocido interno del servidor.

3. **Tipo de documento inválido**  
   **Código de estado**: 400 - BAD_REQUEST  
   Ocurre cuando se envía un tipo de documento no válido (solo se aceptan "C" y "P").

4. **Cliente no encontrado**  
   **Código de estado**: 404 - NOT_FOUND  
   Ocurre cuando los datos enviados en el request body no coinciden con los registros y no se encuentra el cliente.

## Contacto

Para cualquier duda, no dudes en contactarte:

**Johan Sebastian Miranda Gualdron**

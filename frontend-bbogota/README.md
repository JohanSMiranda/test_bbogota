# Proyecto Angular

Este proyecto está desarrollado en **Angular 18** y se ejecuta en **http://localhost:4200/**.

## Funcionalidad

La aplicación presenta un formulario que permite buscar un usuario mediante la selección del tipo de documento y el número de documento.

### Campos del Formulario

- **Tipo de Documento**: Un campo de selección (select) que permite elegir entre:
  - Cédula de Ciudadanía
  - Pasaporte

- **Número de Documento**: Un campo de entrada que solo acepta números. Este campo tiene las siguientes validaciones:
  - Debe contener un mínimo de 8 caracteres y un máximo de 11.

> Nota: Si no se cumplen estas validaciones, el botón "Buscar" no se activará.

## Errores y Mensajes Emergentes

Al hacer clic en el botón "Buscar", si ocurre algún error, se mostrará un modal emergente con los siguientes mensajes:

1. **El número de documento o el tipo no pueden ser nulos o vacíos**  
   Ocurre cuando, por algún motivo, se enviaron campos vacíos o nulos.

2. **Tipo de documento inválido**  
   Ocurre cuando se envía un tipo de documento no válido.

3. **Cliente no encontrado**  
   Ocurre cuando los datos de la consulta enviados no coinciden con ningún usuario registrado.

4. **Error interno del servidor**  
   Ocurre por un error inesperado. Si esto sucede, agradecería tu contacto y una breve descripción para replicar el error.

## Contacto

Para cualquier duda, no dudes en contactarte:

**Johan Sebastian Miranda Gualdron**

package br.com.asoft.apistores.exceptionhandler;


import br.com.asoft.apistores.exceptions.BusinessException;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


//Comente essa tags para desabilitar todos os tratamentos de exceptions.
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    //Caso Um ERRO do codigo na busca não exista ex: localhost:8080/cliente/12
    @ExceptionHandler(EntityNotFoundExceptions.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundExceptions exception, WebRequest request){

        HttpStatus notFound = HttpStatus.NOT_FOUND;

        Problem errorMessage = Problem.builder()
                .type(notFound.series().name())
                .title(notFound.getReasonPhrase())
                .detail(ProblemType.RECURSO_NAO_ENCONTRADO.getType())
                .timestamp(LocalDateTime.now())
                .status(notFound.value())
                .userMessage(exception.getMessage())
                .build();

        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(),errorMessage.getStatus());

    }

    //Caso Um ERRO qualquer de Negocio
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handlerBusinessException(BusinessException exception, WebRequest request){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Problem errorMessage = Problem.builder()
                .type(badRequest.series().name())
                .title(badRequest.getReasonPhrase())
                .detail(ProblemType.RECURSO_NAO_ENCONTRADO.getType())
                .timestamp(LocalDateTime.now())
                .status(badRequest.value())
                .userMessage(exception.getMessage())
                .build();
        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(),errorMessage.getStatus());
    }

    //Caso Um ERRO de campos Obrigatorios Faltando ex: nome , telefone .
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        List<Fields> listCamposErros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map( fieldError ->{

                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                    return Fields.builder()
                        .name(fieldError.getField())
                        .userMessage(message)
                        .build();
                })
                .collect(Collectors.toList());

        Problem errorMessage = Problem.builder()
                .type(badRequest.series().name())
                .title(badRequest.getReasonPhrase())
                .detail(ProblemType.PARAMETRO_OBRIGATORIOS.getType())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .userMessage(ProblemType.HELP.getType())
                .fields(listCamposErros)
                .build();

        //AQUI RETORNO O MEU ResponseEntity PERSONALIZADO.
        return new ResponseEntity<Object>(errorMessage, headers,errorMessage.getStatus());
        //return super.handleMethodArgumentNotValid(ex, headers, status, request); -> ORIGINAL
    }

    //Caso Um ERRO de paramentro invalidos ex: localhost:8080/cliente/21  então o digita cliente/aaa = letras
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Problem errorMessage = Problem.builder()
                .type(badRequest.series().name())
                .title(badRequest.getReasonPhrase())
                .detail(ProblemType.PARAMETRO_INVALIDO.getType())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .userMessage(ProblemType.HELP.getType())
                .build();

        //AQUI RETORNO O MEU ResponseEntity PERSONALIZADO.
        return new ResponseEntity<Object>(errorMessage, headers,errorMessage.getStatus());

       // return super.handleTypeMismatch(ex, headers, status, request); -> CODIGO ORIGINAL DO Override.

    }

    //Caso Um ERRO de URL MAL formatada ex: localhost:8080/cliente/ --  e não passa mais nenhum valor.
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Problem errorMessage = Problem.builder()
                .type(badRequest.series().name())
                .title(badRequest.getReasonPhrase())
                .detail(ProblemType.URL_NAO_ENCONTRADA.getType())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .userMessage(ProblemType.HELP.getType())
                .build();

        //AQUI RETORNO O MEU ResponseEntity PERSONALIZADO.
        return new ResponseEntity<Object>(errorMessage, headers,errorMessage.getStatus());

        //return super.handleNoHandlerFoundException(ex, headers, status, request); ->ORIGINAL
    }

    //Caso Um ERRO de Parâmetros Incorretos ex: Valor é Double , Ai passar uma String por exemplo.
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        Problem errorMessage = Problem.builder()
                .type(badRequest.series().name())
                .title(badRequest.getReasonPhrase())
                .detail(ProblemType.PARAMETRO_INVALIDO.getType())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .userMessage(ProblemType.HELP.getType())
                .build();

        //AQUI RETORNO O MEU ResponseEntity PERSONALIZADO.
        return new ResponseEntity<Object>(errorMessage, headers,errorMessage.getStatus());

       // return super.handleHttpMessageNotReadable(ex, headers, status, request); ->ORIGINAL
    }

    //Caso Um ERRO de qualquer coisa. A Exception Geral.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptionCustom(Exception ex, WebRequest request) {

        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        Problem errorMessage = Problem.builder()
                .type(internalServerError.series().name())
                .title(internalServerError.getReasonPhrase())
                .detail(ProblemType.ERRO_DE_SISTEMA.getType())
                .status(internalServerError.value())
                .timestamp(LocalDateTime.now())
                .userMessage(ProblemType.HELP.getType())
                .build();

        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(),errorMessage.getStatus());
        // return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), internalServerError, request); ->ORIGINAL
    }

}

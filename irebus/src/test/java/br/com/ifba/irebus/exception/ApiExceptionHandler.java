package br.com.ifba.irebus.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;


@RestControllerAdvice //faz com que a classe lide com excecoes na aplicacao spring
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Value(value = "${server.error.include-exception}") //injeta a confg do application.properties
    private boolean printStackTrace;

    @ExceptionHandler(BusinessException.class) //manipula excoes do tipo BussinesException
    @ResponseStatus(HttpStatus.BAD_REQUEST) //define o status hh-ttp como 400
    public ResponseEntity<Object> handleBusinessException(
            final BusinessException businessException,
            final WebRequest request) {
        final String mensagemErro = businessException.getMessage();

        logger.error(mensagemErro, businessException); //vai logar o erro no console

        //retorna mensagem de erro
        return  construirMensagemErro(
                businessException,
                mensagemErro,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    //metodo para resposta de erro
    private ResponseEntity<Object> construirMensagemErro(
            final Exception exception,
            final String message,
            final HttpStatus httpStatus,
            final WebRequest request){

        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (this.printStackTrace){ //verifica se a configutacao esta ativa e inclue a stack trace na resposta
            errorResponse.setStacktrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

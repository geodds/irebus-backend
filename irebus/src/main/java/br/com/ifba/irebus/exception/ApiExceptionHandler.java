package br.com.ifba.irebus.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @Value(value = "${server.error.include-exception}")
    private boolean printStackTrace;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBussinesException(
            final BusinessException businessException,
            final WebRequest request) {
        final String mensagemErro = businessException.getMessage();
        logger.error(mensagemErro, businessException);
        return construirMensagemErro(
                businessException,
                mensagemErro,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
        }

    //metodo para retornar mensagem de erro
    private ResponseEntity<Object> construirMensagemErro(
            final Exception exception,
            final String message,
            final HttpStatus httpStatus,
            final WebRequest request){

        //cria um objeto de ErrorResponse e pega o codigo http e a mensagem
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (this.printStackTrace){ //se for verdadeiro adiciona e pega a stacktrace
            errorResponse.setStacktrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

package com.donat.crypto.exceptions;

import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

@Provider
public class MyExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        MyExceptionMessage exceptionDetails = new MyExceptionMessage();
        exceptionDetails.setStatus(HttpStatus.NOT_FOUND);
        exceptionDetails.setMessage(exception.getMessage());
        exceptionDetails.setTimeStamp(LocalDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity(exceptionDetails).type("application/json").build();
    }

}

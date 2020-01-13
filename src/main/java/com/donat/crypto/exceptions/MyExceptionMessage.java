package com.donat.crypto.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
public class MyExceptionMessage {

    private HttpStatus status;

    private String message;

    private LocalDateTime timeStamp;

}

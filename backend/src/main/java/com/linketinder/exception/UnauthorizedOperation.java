package com.linketinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedOperation extends RuntimeException {

  public UnauthorizedOperation(String message) {
    super(message);
  }

}

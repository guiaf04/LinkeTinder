package com.linketinder.exception;

import groovy.lang.GroovyObjectSupport;

import java.util.Date;
import java.util.Map;

public final class ExceptionResponse extends GroovyObjectSupport {
  public ExceptionResponse(Date timestamp, String message, String details) {
  }

  public ExceptionResponse(Map args) {
  }

  public final Date timestamp() {
    return timestamp;
  }

  public final String message() {
    return message;
  }

  public final String details() {
    return details;
  }
}

package com.docbook.specializations.exception;

public class SpecializationAlreadyPresent extends RuntimeException{

  public SpecializationAlreadyPresent(String msg){
      super(msg);
  }
}

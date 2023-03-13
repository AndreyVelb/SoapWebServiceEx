package com.velb.soapwebserviceex.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class ClientAlreadyExistsException extends RuntimeException{

    public ClientAlreadyExistsException(String message) {
        super(message);
    }

}

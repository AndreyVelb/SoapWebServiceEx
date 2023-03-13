package com.velb.soapwebserviceex.endpoint;

import com.myexample.CreateNewClientRequest;
import com.myexample.CreateNewClientResponse;
import com.myexample.GetClientRequest;
import com.myexample.GetClientResponse;
import com.velb.soapwebserviceex.service.ClientService;
import com.velb.soapwebserviceex.service.JaxbService;
import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class ClientEndpoint {

    private static final String NAMESPACE_URI = "http://www.myexample.com";

    private final ClientService clientService;
    private final JaxbService jaxbService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewClientRequest")
    @ResponsePayload
    public JAXBElement<CreateNewClientResponse> createNewClient(@RequestPayload JAXBElement<CreateNewClientRequest> request) {
        String creationResult = clientService.createNewClient(request.getValue());
        CreateNewClientResponse response = new CreateNewClientResponse();
        response.setOperationResult(creationResult);
        return jaxbService.createJaxbElement(response, CreateNewClientResponse.class);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientRequest")
    @ResponsePayload
    public JAXBElement<GetClientResponse> getClient(@RequestPayload JAXBElement<GetClientRequest> request) {
        GetClientResponse response = new GetClientResponse();
        clientService.findClientById(request.getValue());
        return jaxbService.createJaxbElement(response, GetClientResponse.class);
    }

}



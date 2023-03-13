package com.velb.soapwebserviceex.endpoint;

import com.myexample.ChangeBalanceRequest;
import com.myexample.ChangeBalanceResponse;
import com.myexample.CreateNewAccountRequest;
import com.myexample.CreateNewAccountResponse;
import com.myexample.GetAccountRequest;
import com.myexample.GetAccountResponse;
import com.velb.soapwebserviceex.service.AccountService;
import com.velb.soapwebserviceex.service.JaxbService;
import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class AccountEndpoint {

    private static final String NAMESPACE_URI = "http://www.myexample.com";

    private final AccountService accountService;
    private final JaxbService jaxbService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewAccountRequest")
    @ResponsePayload
    public JAXBElement<CreateNewAccountResponse> createNewAccount(@RequestPayload JAXBElement<CreateNewAccountRequest> request) {
        CreateNewAccountResponse response = new CreateNewAccountResponse();
        response.setOperationResult(accountService.createNewAccount(request.getValue()));
        return jaxbService.createJaxbElement(response, CreateNewAccountResponse.class);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountRequest")
    @ResponsePayload
    public JAXBElement<GetAccountResponse> getAccount(@RequestPayload JAXBElement<GetAccountRequest> request) {
        return jaxbService.createJaxbElement(accountService.getAccount(request.getValue()), GetAccountResponse.class);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changeBalanceRequest")
    @ResponsePayload
    public JAXBElement<ChangeBalanceResponse> changeBalance(@RequestPayload JAXBElement<ChangeBalanceRequest> request) {
        return jaxbService.createJaxbElement(accountService.changeBalance(request.getValue()), ChangeBalanceResponse.class);
    }

}

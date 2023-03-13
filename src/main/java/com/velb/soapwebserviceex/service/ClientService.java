package com.velb.soapwebserviceex.service;

import com.myexample.CreateNewClientRequest;
import com.myexample.GetClientRequest;
import com.myexample.GetClientResponse;
import com.velb.soapwebserviceex.exception.ClientNotFoundException;
import com.velb.soapwebserviceex.model.dto.BankClientDto;
import com.velb.soapwebserviceex.model.entity.BankClientEntity;
import com.velb.soapwebserviceex.exception.ClientAlreadyExistsException;
import com.velb.soapwebserviceex.repository.BankClientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final BankClientEntityRepository bankClientEntityRepository;


    public String createNewClient(CreateNewClientRequest request) {
        try {
            if (isLoginUnique(request.getLogin())) {
                BankClientEntity client = BankClientEntity.builder()
                        .id(UUID.randomUUID())
                        .lastName(request.getLastName())
                        .firstName(request.getFirstName())
                        .login(request.getLogin())
                        .password(request.getPassword())
                        .build();
                bankClientEntityRepository.save(client);
            } else throw new ClientAlreadyExistsException("A client with this login already exists");
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Client successfully registered";
    }


    public GetClientResponse findClientById(GetClientRequest request) {
        GetClientResponse response = new GetClientResponse();
        BankClientDto dto = bankClientEntityRepository.findBankClientDtoById(UUID.fromString(request.getId())).orElseThrow(()
                -> new ClientNotFoundException("This client is not found"));
        response.setClientId(dto.getId().toString());
        response.setFirstName(dto.getFirstName());
        response.setLastName(dto.getLastName());
        response.setLogin(dto.getLogin());
        return response;
    }


    private boolean isLoginUnique(String login) {
        return bankClientEntityRepository.findByLogin(login).isPresent();
    }

}

package com.velb.soapwebserviceex.service;

import com.myexample.ChangeBalanceRequest;
import com.myexample.ChangeBalanceResponse;
import com.myexample.CreateNewAccountRequest;
import com.myexample.GetAccountRequest;
import com.myexample.GetAccountResponse;
import com.velb.soapwebserviceex.exception.AccountNotFoundException;
import com.velb.soapwebserviceex.exception.ClientNotFoundException;
import com.velb.soapwebserviceex.model.entity.BankAccountEntity;
import com.velb.soapwebserviceex.model.entity.BankClientEntity;
import com.velb.soapwebserviceex.repository.BankAccountEntityRepository;
import com.velb.soapwebserviceex.repository.BankClientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final BankAccountEntityRepository bankAccountEntityRepository;
    private final BankClientEntityRepository bankClientEntityRepository;


    public String createNewAccount(CreateNewAccountRequest request) {
        BankClientEntity bankClientEntity = bankClientEntityRepository.findById(UUID.fromString(request.getClientId()))
                .orElseThrow(() -> new ClientNotFoundException("This client is not found"));

        BankAccountEntity bankAccountEntity = BankAccountEntity.builder()
                .id(UUID.randomUUID())
                .bankClient(bankClientEntity)
                .balance(0d)
                .type(request.getType())
                .build();
        bankAccountEntityRepository.save(bankAccountEntity);
        return "Bank account is created";
    }


    public GetAccountResponse getAccount(GetAccountRequest request) {
        BankAccountEntity bankAccountEntity = bankAccountEntityRepository.findById(UUID.fromString(request.getAccountId()))
                .orElseThrow(() -> new AccountNotFoundException("This account not found"));
        GetAccountResponse response = new GetAccountResponse();
        response.setClientId(bankAccountEntity.getBankClient().getId().toString());
        response.setLastName(bankAccountEntity.getBankClient().getLastName());
        response.setFirstName(bankAccountEntity.getBankClient().getFirstName());
        response.setBalance(bankAccountEntity.getBalance());
        response.setType(bankAccountEntity.getType());
        return response;
    }


    public ChangeBalanceResponse changeBalance(ChangeBalanceRequest request) {
        BankAccountEntity bankAccountEntity = bankAccountEntityRepository.findById(UUID.fromString(request.getAccountId()))
                .orElseThrow(() -> new AccountNotFoundException("This account not found"));
        bankAccountEntity.setBalance(bankAccountEntity.getBalance() + request.getBalanceChanges());
        bankAccountEntityRepository.saveAndFlush(bankAccountEntity);
        ChangeBalanceResponse response = new ChangeBalanceResponse();
        response.setOperationResult("Balance is changed");
        response.setAccountId(bankAccountEntity.getId().toString());
        response.setBalance(bankAccountEntity.getBalance());
        return response;
    }

}

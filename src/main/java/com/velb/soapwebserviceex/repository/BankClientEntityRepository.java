package com.velb.soapwebserviceex.repository;

import com.velb.soapwebserviceex.model.dto.BankClientDto;
import com.velb.soapwebserviceex.model.entity.BankClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankClientEntityRepository extends JpaRepository<BankClientEntity, UUID> {

    Optional<BankClientEntity> findByLogin(String login);

    @Query("SELECT new com.velb.soapwebserviceex.model.dto.BankClientDto(c.id, c.lastName, c.firstName, c.login) " +
            "FROM BankClientEntity c " +
            "WHERE c.id = :id")
    Optional<BankClientDto> findBankClientDtoById(UUID id);

}



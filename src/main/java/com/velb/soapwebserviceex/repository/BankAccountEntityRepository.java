package com.velb.soapwebserviceex.repository;

import com.velb.soapwebserviceex.model.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankAccountEntityRepository extends JpaRepository<BankAccountEntity, UUID> {
}

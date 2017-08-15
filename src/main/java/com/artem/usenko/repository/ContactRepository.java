package com.artem.usenko.repository;

import com.artem.usenko.model.dataBase.ContactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ContactRepository extends CrudRepository<ContactEntity, Integer> {
    List<ContactEntity> getContactsByUserId(Integer id);
}

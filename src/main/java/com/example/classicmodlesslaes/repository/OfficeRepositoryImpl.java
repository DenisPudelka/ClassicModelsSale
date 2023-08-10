package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.repository.interfaces.OfficeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OfficeRepositoryImpl implements OfficeRepository {

    private EntityManager entityManager;

    @Autowired
    public OfficeRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public Office getOfficeById(String id) {
        Office office = entityManager.find(Office.class, id);
        return office;
    }

    @Override
    @Transactional
    public void saveOffice(Office office) {
        entityManager.persist(office);
    }

    @Override
    @Transactional
    public void updateOffice(Office office) {
        entityManager.merge(office);
    }

    @Override
    @Transactional
    public void deleteOffice(String id) {
        Office office = entityManager.find(Office.class, id);
        entityManager.remove(office);
    }
}

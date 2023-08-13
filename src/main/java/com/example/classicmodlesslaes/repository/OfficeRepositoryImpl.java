package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.model.OrderDetail;
import com.example.classicmodlesslaes.repository.interfaces.OfficeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeRepositoryImpl implements OfficeRepository {

    private EntityManager entityManager;

    @Autowired
    public OfficeRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional(readOnly = true)
    public Office getOfficeById(String id) {
        Office office = entityManager.find(Office.class, id);
        return office;
    }

    @Override
    @Transactional
    public Office saveOffice(Office office) {
        entityManager.persist(office);
        return office;
    }

    @Override
    @Transactional
    public Office updateOffice(Office office) {
        return entityManager.merge(office);
    }

    @Override
    @Transactional
    public void deleteOffice(String id) {
        Office office = entityManager.find(Office.class, id);
        entityManager.remove(office);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> getAllOffices() {
        List<Office> offices = new ArrayList<>();
        TypedQuery<Office> query = entityManager.createQuery("SELECT o FROM Office o", Office.class);
        offices = query.getResultList();
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesByCity(String city) {
        List<Office> offices = new ArrayList<>();
        TypedQuery<Office> query = entityManager.createQuery("select o from Office o where o.city = :cityName", Office.class);
        query.setParameter("cityName", city);
        offices = query.getResultList();
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesByCountry(String country) {
        List<Office> offices = new ArrayList<>();
        TypedQuery<Office> query = entityManager.createQuery("select o from Office o where o.country = :countryName", Office.class);
        query.setParameter("countryName", country);
        offices = query.getResultList();
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesByTerritory(String territory) {
        List<Office> offices = new ArrayList<>();
        TypedQuery<Office> query = entityManager.createQuery("select o from Office o where o.territory = :territoryName", Office.class);
        query.setParameter("territoryName", territory);
        offices = query.getResultList();
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesWithPhonePattern(String pattern) {
        List<Office> offices = new ArrayList<>();
        TypedQuery<Office> query = entityManager.createQuery("SELECT o FROM Office o where o.phone LIKE :pattern", Office.class);
        query.setParameter("pattern", "%" + pattern + "%");
        offices = query.getResultList();
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> searchOfficesByAddress(String keyword) {
        List<Office> offices = new ArrayList<>();
        TypedQuery<Office> query = entityManager.createQuery("SELECT o FROM Office o where o.addressLineOne LIKE :keyword or o.addressLineTwo LIKE :keyword", Office.class);
        query.setParameter("keyword", "%" + keyword + "%");
        offices = query.getResultList();
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public int countOfficesByCountry(String country) {
        TypedQuery<Long> query = entityManager.createQuery("select count(o) from Office o where o.country = :country", Long.class);
        query.setParameter("country", country);
        return (query.getSingleResult()).intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllTerritories() {
        List<String> offices = new ArrayList<>();
        TypedQuery<String> query = entityManager.createQuery("SELECT DISTINCT o.territory FROM Office o", String.class);
        offices = query.getResultList();
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> countOfficesByTerritory() {
        TypedQuery<Object[]> query = entityManager.createQuery("SELECT o.territory, count(o) from Office o GROUP BY o.territory ORDER BY o.territory", Object[].class);
        List<Object[]> result = query.getResultList();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> countEmployeesPerOffice() {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT o.officeCode, COUNT(e) " +
                        "FROM Office o " +
                        "LEFT JOIN o.employees e " +
                        "GROUP BY o.officeCode " +
                        "ORDER BY COUNT(e) DESC",
                Object[].class
        );
        return query.getResultList();
    }


}

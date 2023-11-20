package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.repository.interfaces.OfficeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Office saveOffice(Office office) {
        entityManager.persist(office);
        return office;
    }

    @Override
    public Office updateOffice(Office office) {
        return entityManager.merge(office);
    }

    @Override
    public boolean deleteOffice(String id) {
        Office office = entityManager.find(Office.class, id);
        if(office != null) {
            entityManager.remove(office);
            return true;
        }
        return false;
    }

    @Override
    public List<Office> getAllOffices() {
        TypedQuery<Office> query = entityManager.createQuery("SELECT o FROM Office o", Office.class);
        return query.getResultList();
    }

    @Override
    public List<Office> findOfficesByCity(String city) {
        TypedQuery<Office> query = entityManager.createQuery("select o from Office o where o.city = :cityName", Office.class);
        query.setParameter("cityName", city);
        return query.getResultList();
    }

    @Override
    public List<Office> findOfficesByCountry(String country) {
        TypedQuery<Office> query = entityManager.createQuery("select o from Office o where o.country = :countryName", Office.class);
        query.setParameter("countryName", country);
        return query.getResultList();
    }

    @Override
    public List<Office> findOfficesByTerritory(String territory) {
        TypedQuery<Office> query = entityManager.createQuery("select o from Office o where o.territory = :territoryName", Office.class);
        query.setParameter("territoryName", territory);
        return query.getResultList();
    }

    @Override
    public List<Office> findOfficesWithPhonePattern(String pattern) {
        TypedQuery<Office> query = entityManager.createQuery("SELECT o FROM Office o where o.phone LIKE :pattern", Office.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }

    @Override
    public List<Office> searchOfficesByAddress(String keyword) {
        TypedQuery<Office> query = entityManager.createQuery("SELECT o FROM Office o where o.addressLineOne LIKE :keyword or o.addressLineTwo LIKE :keyword", Office.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public int countOfficesByCountry(String country) {
        TypedQuery<Long> query = entityManager.createQuery("select count(o) from Office o where o.country = :country", Long.class);
        query.setParameter("country", country);
        return (query.getSingleResult()).intValue();
    }

    @Override
    public List<String> findAllTerritories() {
        TypedQuery<String> query = entityManager.createQuery("SELECT DISTINCT o.territory FROM Office o", String.class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> countOfficesByTerritory() {
        TypedQuery<Object[]> query = entityManager.createQuery("SELECT o.territory, count(o) from Office o GROUP BY o.territory ORDER BY o.territory", Object[].class);
        List<Object[]> result = query.getResultList();
        return result;
    }

    @Override
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

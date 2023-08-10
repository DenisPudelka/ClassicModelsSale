package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Office;

public interface OfficeRepository {
    Office getOfficeById(String id);
    void saveOffice(Office office);
    void updateOffice(Office office);
    void deleteOffice(String id);
}

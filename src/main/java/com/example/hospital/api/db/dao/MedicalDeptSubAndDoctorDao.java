package com.example.hospital.api.db.dao;

import com.example.hospital.api.db.pojo.MedicalDeptSubAndDoctorEntity;

import java.util.Map;

public interface MedicalDeptSubAndDoctorDao {
    public void insert(MedicalDeptSubAndDoctorEntity entity);
    public void updateDoctorSubDept(Map param);
   
}





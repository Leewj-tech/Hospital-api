package com.example.hospital.api.service;

import com.example.hospital.api.db.pojo.DoctorWorkPlanScheduleEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//-*- coding: utf-8 -*-
// time: 2023/3/28 23:58
// file: DoctorWorkPlanScheduleService.java
// author: LeeWJ
public interface DoctorWorkPlanScheduleService {
    public void insert(ArrayList<DoctorWorkPlanScheduleEntity> list);
    public ArrayList searchDeptSubSchedule(Map param);
    public HashMap searchByWorkPlanId(int workPlanId);
    public void updateSchedule(Map param);
    public void deleteByWorkPlanId(int workPlanId);
}

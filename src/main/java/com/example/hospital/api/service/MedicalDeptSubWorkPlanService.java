package com.example.hospital.api.service;

import cn.hutool.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

//-*- coding: utf-8 -*-
// time: 2023/3/28 18:42
// file: MedicalDeptSubWorkPlanService.java
// author: LeeWJ
public interface MedicalDeptSubWorkPlanService {
    public JSONArray searchWorkPlanInRange(Map param, ArrayList datalist);

    public String insert(Map param);
    public void deleteWorkPlan(int workPlanId);
}

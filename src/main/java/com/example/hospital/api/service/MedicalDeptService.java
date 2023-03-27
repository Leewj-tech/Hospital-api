package com.example.hospital.api.service;

import cn.hutool.db.Page;
import com.example.hospital.api.common.PageUtils;
import com.example.hospital.api.db.pojo.MedicalDeptEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//-*- coding: utf-8 -*-
// time: 2023/3/24 19:18
// file: MedicalDeptService.java
// author: LeeWJ

public interface MedicalDeptService {
    public HashMap searchDeptAndSub();
    public ArrayList<HashMap> searchAll();
    public PageUtils searchByPage(Map param);
    public void insert(MedicalDeptEntity entity);
    public HashMap searchById(int id);
    public void update(MedicalDeptEntity entity);
    public void deleteByIds(Integer[] ids);
}

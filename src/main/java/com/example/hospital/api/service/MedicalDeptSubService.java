package com.example.hospital.api.service;

import cn.hutool.db.Page;
import com.example.hospital.api.common.PageUtils;
import com.example.hospital.api.db.pojo.MedicalDeptEntity;
import com.example.hospital.api.db.pojo.MedicalDeptSubEntity;

import java.util.Map;

//-*- coding: utf-8 -*-
// time: 2023/3/26 19:59
// file: MedicalDeptSubService.java
// author: LeeWJ
public interface MedicalDeptSubService {
    public PageUtils searchByPage(Map param);
    public void insert(MedicalDeptSubEntity entity);
}

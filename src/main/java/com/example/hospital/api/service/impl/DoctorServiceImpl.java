package com.example.hospital.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.example.hospital.api.common.PageUtils;
import com.example.hospital.api.db.dao.DoctorDao;
import com.example.hospital.api.db.dao.DoctorWorkPlanScheduleDao;
import com.example.hospital.api.db.dao.MedicalDeptSubAndDoctorDao;
import com.example.hospital.api.db.pojo.DoctorEntity;
import com.example.hospital.api.db.pojo.MedicalDeptSubAndDoctorEntity;
import com.example.hospital.api.exception.HospitalException;
import com.example.hospital.api.service.DoctorService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//-*- coding: utf-8 -*-
// time: 2023/3/23 10:42
// file: DoctorServiceImpl.java
// author: LeeWJ

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Resource
    private DoctorDao doctorDao;

    @Resource
    private MedicalDeptSubAndDoctorDao medicalDeptSubAndDoctorDao;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;


    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = null;
        long count = doctorDao.searchCount(param);
        if(count > 0) {
            list = doctorDao.searchByPage(param);
        } else {
            list = new ArrayList<>();
        }
        int page = MapUtil.getInt(param,"page");
        int length = MapUtil.getInt(param,"length");
        PageUtils pageUtils = new PageUtils(list,count,page,length);
        return pageUtils;
    }

    @Override
    public HashMap searchContent(int id) {
        HashMap map = doctorDao.searchContent(id);
        //把tag转换成json格式
        JSONArray tag = JSONUtil.parseArray(MapUtil.getStr(map, "tag"));
        map.replace("tag",tag);
        return map;
    }

    @Override
    @Transactional
    public void updatePhoto(MultipartFile file, Integer doctorId) {
        try{
            String filename = "doctor-" + doctorId + ".jpg";
            //然后在Minio中保存照片
            MinioClient client = new MinioClient.Builder().endpoint(endpoint)
                    .credentials(accessKey,secretKey).build();

            client.putObject(PutObjectArgs.builder().bucket("hospital")
                    .object("doctor/" + filename)
                    .stream(file.getInputStream(),-1,5 * 1024 * 1024)
                    .contentType("image/jpeg").build());

            //更新数据库中医生表photo字段
            doctorDao.updatePhoto(new HashMap() {
                {
                    put("id",doctorId);
                    put("photo","/doctor/" + filename);
                }
            });
        } catch (Exception e) {
            log.error("保存医生照片失败",e);
            throw new HospitalException("保存医生照片失败");
        }
    }

    @Override
    public void insert(Map param) {
        DoctorEntity entity_1 = BeanUtil.toBean(param, DoctorEntity.class);
        doctorDao.insert(entity_1);

        String uuid = entity_1.getUuid();

        Integer doctorId = doctorDao.searchIdByUuid(uuid);

        int subId = MapUtil.getInt(param, "subId");

        MedicalDeptSubAndDoctorEntity entity_2 = new MedicalDeptSubAndDoctorEntity();
        entity_2.setDeptSubId(subId);
        entity_2.setDoctorId(doctorId);
        medicalDeptSubAndDoctorDao.insert(entity_2);
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = doctorDao.searchById(id);
        //tag是String，记得转成json
        String tag = MapUtil.getStr(map, "tag");
        JSONArray array = JSONUtil.parseArray(tag);
        map.replace("tag",array);
        return map;
    }

    @Override
    @Transactional
    public void update(Map param) {
        //这是更新doctor表
        doctorDao.update(param);
        param = MapUtil.renameKey(param,"id","doctorId");
        //现在更新诊室表
        medicalDeptSubAndDoctorDao.updateDoctorSubDept(param);
    }

    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        doctorDao.deleteByIds(ids);
    }


}

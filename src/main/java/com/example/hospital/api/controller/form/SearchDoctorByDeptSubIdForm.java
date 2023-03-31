package com.example.hospital.api.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//-*- coding: utf-8 -*-
// time: 2023/3/29 9:36
// file: SearchDoctorByDeptSubIdForm.java
// author: LeeWJ
@Data
public class SearchDoctorByDeptSubIdForm {
    @NotNull(message = "deptSubId不能为空")
    @Min(value = 1, message = "deptSubId不能小于1")
    private Integer deptSubId;
}

package com.example.hospital.api.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//-*- coding: utf-8 -*-
// time: 2023/3/26 18:01
// file: SearchMedicalDeptByIdForm.java
// author: LeeWJ
@Data
public class SearchMedicalDeptByIdForm {
    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;
}

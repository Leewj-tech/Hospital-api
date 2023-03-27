package com.example.hospital.api.controller.form;

//-*- coding: utf-8 -*-
// time: 2023/3/24 9:24
// file: SearchDoctorContentForm.java
// author: LeeWJ

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SearchDoctorContentForm {
    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;
}

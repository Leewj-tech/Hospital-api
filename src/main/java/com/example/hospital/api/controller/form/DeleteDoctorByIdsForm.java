package com.example.hospital.api.controller.form;

//-*- coding: utf-8 -*-
// time: 2023/3/25 18:29
// file: DeleteDoctorByIdsForm.java
// author: LeeWJ

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DeleteDoctorByIdsForm {
    @NotEmpty(message = "ids不能为空")
    private Integer[] ids;
}

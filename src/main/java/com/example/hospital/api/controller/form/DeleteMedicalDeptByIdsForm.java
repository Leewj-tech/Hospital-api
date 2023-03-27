package com.example.hospital.api.controller.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

//-*- coding: utf-8 -*-
// time: 2023/3/26 19:21
// file: DeleteMedicalDeptByIdsForm.java
// author: LeeWJ
@Data
public class DeleteMedicalDeptByIdsForm {

    @NotEmpty(message = "ids不能为空")
    private Integer[] ids;
}

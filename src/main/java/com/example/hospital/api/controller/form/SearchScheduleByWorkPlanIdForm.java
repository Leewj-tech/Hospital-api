package com.example.hospital.api.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//-*- coding: utf-8 -*-
// time: 2023/3/31 0:32
// file: SearchScheduleByWorkPlanIdForm.java
// author: LeeWJ
@Data
public class SearchScheduleByWorkPlanIdForm {
    @NotNull(message = "workPlanId不能为空")
    @Min(value = 1, message = "workPlanId不能小于1")
    private Integer workPlanId;
}

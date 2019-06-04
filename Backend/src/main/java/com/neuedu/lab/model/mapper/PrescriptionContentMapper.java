package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.PrescriptionContent;

public interface PrescriptionContentMapper {
    void addPrescriptionContent(PrescriptionContent prescriptionContent);//加药
    void deletePrescriptionContents(Integer prescription_id);//删除处方
    void deletePrescriptionContent(Integer prescription_content_id);//删药
}

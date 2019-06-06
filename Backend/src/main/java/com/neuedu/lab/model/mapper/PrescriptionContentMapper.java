package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.PrescriptionContent;

import java.util.List;

public interface PrescriptionContentMapper {
    void addPrescriptionContent(PrescriptionContent prescriptionContent);//加药
    void deletePrescriptionContents(Integer prescription_id);//删除处方
    void deletePrescriptionContent(Integer prescription_content_id);//删药

    //供退药使用
    List<PrescriptionContent> getPrescriptionContents(Integer prescription_id);

    PrescriptionContent getPrescriptionContent(Integer prescription_id, Integer prescription_medicine_id);
}

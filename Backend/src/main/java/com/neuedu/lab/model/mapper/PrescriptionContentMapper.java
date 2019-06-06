package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Prescription;
import com.neuedu.lab.model.po.PrescriptionContent;

import java.util.List;

public interface PrescriptionContentMapper {
    void addPrescriptionContent(PrescriptionContent prescriptionContent);//加药
    void deletePrescriptionContents(Integer prescription_id);//删除处方
    void deletePrescriptionContent(Integer prescription_content_id);//删药

    //供退药使用，仅返回未被消费的药品
    List<PrescriptionContent> getPrescriptionContentsNew(Integer prescription_id);

    List<PrescriptionContent> getPrescriptionContents(Integer prescription_id);

    PrescriptionContent getPrescriptionContent(Integer prescription_id, String prescription_medicine_id);

    //供退药使用,返回一个处方下一种药品的所有记录（数量）
    Integer getPrescriptionContentNum (PrescriptionContent prescriptionContent);

    //供退药使用，返回数量为正的药品记录
    PrescriptionContent getPrescriptionContentPositive(PrescriptionContent prescriptionContent);
}

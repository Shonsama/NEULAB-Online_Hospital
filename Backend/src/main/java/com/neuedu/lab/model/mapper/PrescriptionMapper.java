package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Prescription;

import java.util.List;

public interface PrescriptionMapper {

    void addPrescription(Prescription prescription);

    void deletePrescription(Integer prescription_id);

    void updatePrescriptionState(Integer prescription_id,String prescription_execute_state);

    Prescription getPrescription(Integer prescription_id);

    List<Prescription> getPrescriptionByRegisterId(Integer prescription_register_info_id);

    //退费部分使用
    void updatePrescriptionFee(Prescription prescription);

    List<Prescription> getPrescriptionsInPayCondition(Integer prescription_register_info_id);
    List<Prescription> getPrescriptionsNotInPayCondition(Integer prescription_register_info_id);

}

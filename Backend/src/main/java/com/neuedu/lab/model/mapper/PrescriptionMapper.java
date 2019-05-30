package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Prescription;

public interface PrescriptionMapper {

    void addPrescription(Prescription prescription);

    void deletePrescription(Integer prescription_id);

    void updatePrescriptionState(Integer prescription_id,String prescription_execute_state);

}

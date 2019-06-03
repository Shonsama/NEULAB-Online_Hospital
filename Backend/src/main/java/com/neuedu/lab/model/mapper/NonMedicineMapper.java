package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.NonMedicine;

import java.util.List;

public interface NonMedicineMapper {
    public List<NonMedicine> getAllNonMedicines();
    public void addNonMedicine(NonMedicine nonMedicine);
    public void deleteNonMedicine(String non_medicine_id);
    public void updateNonMedicine(NonMedicine nonMedicine);
    public List<NonMedicine> getNonMedicine(String non_medicine_id);

}

package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Medicine;
import java.util.List;

public interface MedicineMapper {
    public List<Medicine> getAllMedicines();
    public void addMedicine(Medicine medicine);
    public void deleteMedicine(Medicine medicine);
    public void updateMedicine(Medicine medicine);
    public List<Medicine> getMedicine(String medicine_id);


}

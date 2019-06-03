package com.neuedu.lab.model.service;

import com.neuedu.lab.model.mapper.MedicineMapper;
import com.neuedu.lab.model.po.Medicine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicineService {
    @Resource
    private MedicineMapper medicineMapper;

    public List<Medicine> getAllMedicines(){
        return medicineMapper.getAllMedicines();
    }

    public List<Medicine> getMedicine(String medicine_id){
        return medicineMapper.getMedicine(medicine_id);
    }

    public boolean addMedicine(Medicine medicine){
        try {
            medicineMapper.addMedicine(medicine);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteMedicine(String medicine_id){
        try {
            medicineMapper.deleteMedicine(medicine_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateMedicine(Medicine medicine){
        try {
            medicineMapper.updateMedicine(medicine);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

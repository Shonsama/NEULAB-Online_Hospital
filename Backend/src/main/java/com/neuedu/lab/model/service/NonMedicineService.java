package com.neuedu.lab.model.service;


import com.neuedu.lab.model.mapper.NonMedicineMapper;
import com.neuedu.lab.model.po.NonMedicine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NonMedicineService {

    @Resource
    private NonMedicineMapper nonMedicineMapper;

    public List<NonMedicine> getAllNonMedicines(){
        return nonMedicineMapper.getAllNonMedicines();
    }

    public List<NonMedicine> getNonMedicine(String non_medicine_id){
        return nonMedicineMapper.getNonMedicine(non_medicine_id);
    }

    public boolean addNonMedicine(NonMedicine nonMedicine){
        try {
            nonMedicineMapper.addNonMedicine(nonMedicine);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteNonMedicine(String non_medicine_id){
        try {
            nonMedicineMapper.deleteNonMedicine(non_medicine_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateNonMedicine(NonMedicine nonMedicine){
        try {
            nonMedicineMapper.updateNonMedicine(nonMedicine);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

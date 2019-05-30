package com.neuedu.lab;

import com.neuedu.lab.model.po.DiseaseInfo;
import com.neuedu.lab.model.po.Medicine;
import com.neuedu.lab.model.po.NonMedicine;
import com.neuedu.lab.model.service.DiseaseInfoService;
import com.neuedu.lab.model.service.MedicineService;
import com.neuedu.lab.model.service.NonMedicineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabApplication.class)
public class TestAll {
    @Autowired
    private DiseaseInfoService diseaseInfoService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private NonMedicineService nonMedicineService;
    @Test
    public void testAddDiseaseInfo(){
        DiseaseInfo diseaseInfo = new DiseaseInfo();
        diseaseInfo.setDisease_id("A");
        diseaseInfo.setDisease_type("B");
        diseaseInfo.setDisease_name("C");
        diseaseInfo.setDisease_icd("D");

        assertTrue(diseaseInfoService.addDisease(diseaseInfo));
    }

    @Test
    public void testDeleteDisease(){
        DiseaseInfo diseaseInfo = diseaseInfoService.getDisease("A").get(0);
        assertTrue(diseaseInfoService.deleteDisease(diseaseInfo));
    }

    @Test
    public void testAddMedicine(){
        Medicine medicine = new Medicine();
        medicine.setMedicine_factory("A");
        medicine.setMedicine_formulation("B");
        medicine.setMedicine_id("C");
        medicine.setMedicine_name("D");
        medicine.setMedicine_pinyin("E");
        medicine.setMedicine_specifications("1");
        medicine.setMedicine_type("2");
        medicine.setMedicine_unit("3");
        medicine.setMedicine_unit_price(new BigDecimal(3.6));
        medicine.setMedicine_usage("4");
        assertTrue(medicineService.addMedicine(medicine));
    }

    @Test
    public void testDeleteMedicine(){
        Medicine medicine = medicineService.getMedicine("C").get(0);
        assertTrue(medicineService.deleteMedicine(medicine));
    }

    @Test
    public void testAddNonMedicine(){
        NonMedicine nonMedicine = new NonMedicine();
        nonMedicine.setNon_medicine_id("A");
        nonMedicine.setNon_medicine_type("B");
        nonMedicine.setNon_medicine_name("C");
        nonMedicine.setNon_medicine_unit_price(new BigDecimal(99.8));
        nonMedicine.setNon_medicine_specification("D");
        assertTrue(nonMedicineService.addNonMedicine(nonMedicine));
    }

    @Test
    public void testDeleteNonMedicine(){
        NonMedicine nonMedicine = nonMedicineService.getNonMedicine("A").get(0);
        assertTrue(nonMedicineService.deleteNonMedicine(nonMedicine));
    }
}

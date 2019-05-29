package com.neuedu.lab;

import com.neuedu.lab.model.po.DiseaseInfo;
import com.neuedu.lab.model.service.DiseaseInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabApplication.class)
public class TestAll {
    @Autowired
    private DiseaseInfoService diseaseInfoService;

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
}

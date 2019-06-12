package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.Prescription;
import com.neuedu.lab.model.po.PrescriptionContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.neuedu.lab.Utils.ConstantDefinition.*;
import static com.neuedu.lab.Utils.ConstantUtils.*;

@Service
public class MedicineDepartService {
    @Resource
    MedicalSkillMapper medicalSkillMapper;
    @Resource
    RegisterMapper registerMapper;
    @Resource
    PatientMapper patientMapper;
    @Resource
    PrescriptionMapper prescriptionMapper;
    @Resource
    PrescriptionContentMapper prescriptionContentMapper;


    public JSONObject getPrescriptionByPatientIdByPayTime(Integer register_info_patient_id,Date start_time, Date end_time){
        return ConstantUtils.responseSuccess(prescriptionMapper.getPrescriptionByPatientIdByPayTime(register_info_patient_id, PRESCRIPTION_EXECUTE_STATE[3],start_time,end_time));
    }

    public JSONObject getAllPatients(){
        return ConstantUtils.responseSuccess(prescriptionMapper.getAllPatients(PRESCRIPTION_EXECUTE_STATE[3],PRESCRIPTION_EXECUTE_STATE[4]));
    }

    public JSONObject sendMedicine(Integer prescription_id, Integer prescription_execute_doctor_id){
        Prescription prescription = prescriptionMapper.getPrescription(prescription_id);
        if(prescription==null){
            return responseFail("无此处方记录");
        }else if(!prescription.getPrescription_execute_state().equals(PRESCRIPTION_EXECUTE_STATE[3])){
            return responseFail("此处方状态为["+prescription.getPrescription_execute_state()+"],不可发药",prescription);
        }
        else {

            //更新状态和发药时间
            prescription.setPrescription_execute_state(PRESCRIPTION_EXECUTE_STATE[4]);
            prescription.setPrescription_sent_time(new Date());
            prescription.setPrescription_execute_doctor_id(prescription_execute_doctor_id);
            prescriptionMapper.updatePrescription(prescription);
            return ConstantUtils.responseSuccess(prescriptionMapper.getPrescription(prescription_id));
        }
    }


    @Transactional
    public JSONObject returnMedicine(Integer prescription_id, String prescription_medicine_id, Integer prescription_num){
        Prescription prescription = prescriptionMapper.getPrescription(prescription_id);

        PrescriptionContent prescriptionContent = new PrescriptionContent();
        prescriptionContent.setPrescription_id(prescription_id);
        prescriptionContent.setPrescription_medicine_id(prescription_medicine_id);
        prescriptionContent.setPrescription_num(prescription_num);

        //首先查看此条药品记录是否存在 根据处方ID和药物ID（不能根据药品记录ID）
//        Integer prescriptionContentNum = prescriptionContentMapper.getPrescriptionContentNum(prescriptionContent);
        PrescriptionContent prescriptionContentBefore = prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent);

        if(prescriptionContentBefore == null){
            return responseFail("该处方药品不存在");
        }
        //查看药品状态应该是已领药状态
        if(!prescription.getPrescription_execute_state().equals(PRESCRIPTION_EXECUTE_STATE[4])){//已领药
            return responseFail("该处方状态为["+prescription.getPrescription_execute_state()+"],不可退药",null);
        }

        //查看药品数量是否满足
        if(prescriptionContentBefore.getPrescription_refund_available_num()  < prescriptionContent.getPrescription_num()){
            return responseFail("药品数量大于可退费数量",null);
        }
        else {
            try{
                //修改可退药数量
                prescriptionContentBefore.setPrescription_refund_available_num(
                        prescriptionContentBefore.getPrescription_refund_available_num() - prescriptionContent.getPrescription_num()
                );


                //更新其可退药数量
                prescriptionContentMapper.updatePrescriptionContent(prescriptionContentBefore);


                //再次获取最初的药品记录
                PrescriptionContent prescriptionContentToAdd = prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent);

                //改变数量
                prescriptionContentToAdd.setPrescription_num(ConstantUtils.convertToNegtive(prescriptionContent.getPrescription_num()));


                //增加退药的数量为负的药品记录
                prescriptionContentMapper.addPrescriptionContent(prescriptionContentToAdd);
            }catch (RuntimeException e){
                e.printStackTrace();
                return responseFail("退药失败");
            }
            return responseSuccess(prescriptionContentMapper.getPrescriptionContentPositive(prescriptionContent));
        }
    }


    public JSONObject getSentPrescription(Integer patient_id, Date start_time, Date end_time) {
        List<Prescription> prescriptions;
        try{
            prescriptions = prescriptionMapper.getPrescriptionByPatientIdBySentTime
                    (patient_id,PRESCRIPTION_EXECUTE_STATE[4],start_time,end_time);//已领药
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(prescriptions);
    }

    public JSONObject getPrescriptionContentsByPrescriptionId(Integer prescription_id){
        List<PrescriptionContent> prescriptionContents;
        try{
            prescriptionContents = prescriptionContentMapper.getPrescriptionContents(prescription_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(prescriptionContents);
    }



}

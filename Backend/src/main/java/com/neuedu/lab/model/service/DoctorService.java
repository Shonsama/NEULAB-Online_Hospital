package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class DoctorService {
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private RegisterMapper registerMapper;
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private MedicalSkillMapper medicalSkillMapper;
    @Resource
    private PrescriptionMapper prescriptionMapper;
    @Resource
    private BillMapper billMapper;

    @Resource
    private DiagnoseMapper diagnoseMapper;

    @Resource
    private PrescriptionContentMapper prescriptionContentMapper;


    //查询一个医生的所有挂号信息
    public List<Register> getAllRegisters(Integer doctor_id){
        return registerMapper.getRegisterByDoctorId(doctor_id);
    }

    //查询一个科室的所有挂号信息
    public List<Register> getAllDepartmentRegisters(Integer department_id) {
        return registerMapper.getRegisterByDepartmentId(department_id);
    }

    //根据患者姓名和医生ID查询挂号信息
    public List<Register> getRegisterByDoctorIdAndPatientName(Integer doctor_id, String patient_name){
        return registerMapper.getRegisterByDoctorIdAndPatientName(doctor_id,patient_name);
    }


    //接诊
    public boolean treat(Integer register_id){
        try{
            doctorMapper.treat(register_id, ConstantDefinition.REGISTER_STATE[1]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //填写门诊病历首页
    @Transactional
    public boolean submitRecord(Record record){
        try {
            Boolean record_final_sumbit = recordMapper.getRecordFinalSubmitById(record.getRecord_id());
            if(record_final_sumbit== null){
                recordMapper.addRecord(record);

                List<Diagnose> diagnoses = record.getDiagnoses();
                if(diagnoses!=null){ //如果有初步诊断病
                    for(int i = 0; i<diagnoses.size(); i++){
                        diagnoses.get(i).setDiagnose_type(ConstantDefinition.DIAGNOSE_TYPE[0]);
                        diagnoseMapper.addDiagnose(diagnoses.get(i));
                    }
                }
                return true;
            }
            if(record_final_sumbit){
                return false;
            }
            else {
                recordMapper.updateRecord(record);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //新增医技项目
    public boolean addMedicalSkill(MedicalSkill medicalSkill){
        try{
            medicalSkill.setMedical_skill_excute_state(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[0]);
            medicalSkillMapper.addMedicalSkill(medicalSkill);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //删除医技项目
    public boolean deleteMedicalSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.deleteMedicalSkill(medical_skill_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //开立医技项目
    public boolean startMedicalSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.updateMedicalSkillState(medical_skill_id,ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[1]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //作废医技项目
    public boolean endMedicalSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.updateMedicalSkillState(medical_skill_id,ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[2]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //查看检查结果
    public MedicalSkill checkResult(Integer medical_skill_id){
        return medicalSkillMapper.getMedicalSkill( medical_skill_id);
    }

    //查看初步诊断信息
    public Record getRecord(Integer record_id){
        return recordMapper.getRecordById(record_id);
    }

    //提交最终确诊结果
    @Transactional
    public boolean submitFinalDiagnose(List<Diagnose> diagnoses){
        try {
            for(int i = 0; i<diagnoses.size(); i++){
                diagnoses.get(i).setDiagnose_type(ConstantDefinition.DIAGNOSE_TYPE[1]);
                diagnoseMapper.addDiagnose(diagnoses.get(i));
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //开立处方
    public boolean addPrescription(Prescription prescription){
        try{
            prescription.setPrescription_execute_state(ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[0]);
            prescriptionMapper.addPrescription(prescription);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //删除处方
    @Transactional
    public boolean deletePrescription(Integer prescription_id){
        try{
            prescriptionContentMapper.deletePrescriptionContents(prescription_id);
            prescriptionMapper.deletePrescription(prescription_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //发送处方,作废处方
    public boolean sendPrescription(Integer prescription_id) {
        try {
            prescriptionMapper.updatePrescriptionState(prescription_id,ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[1]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean endPrescription(Integer prescription_id) {
        try {
            prescriptionMapper.updatePrescriptionState(prescription_id,ConstantDefinition.PRESCRIPTION_EXECUTE_STATE[2]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //给某个处方增加药品
    public boolean addPrescriptionContent(PrescriptionContent prescriptionContent){
        try {
            prescriptionContentMapper.addPrescriptionContent(prescriptionContent);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //给某个处方删除药品
    public boolean deletePrescriptionContent(Integer prescriptionid) {
        try {
            prescriptionContentMapper.deletePrescriptionContent(prescriptionid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //诊毕
    public boolean finish(Integer register_id){
        try{
            registerMapper.updateRegisterState(register_id,ConstantDefinition.REGISTER_STATE[2]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public JSONArray getFeeRecords(Integer patient_record_id) {
        JSONArray data = new JSONArray();
        List<Register> registers = registerMapper.getRegistersByPatientId(patient_record_id);
        for(int i = 0; i<registers.size();i++){
            data.add(billMapper.getBillForOneRecord(registers.get(i).getRegister_info_id()));
        }
        return data;
    }


}

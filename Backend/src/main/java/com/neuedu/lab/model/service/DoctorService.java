package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantDefinition;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Resource
    private PatientMapper patientMapper;


    //查询一个医生的所有挂号信息
    public JSONObject getAllRegisters(Integer doctor_id){
        List<Register> registers;
        try{
            registers  = registerMapper.getRegisterByDoctorId(doctor_id);
            for(Register register: registers){
                register.setPatient(patientMapper.getPatientByRecordId(register.getRegister_info_id()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail(null);
        }

        return ConstantUtils.responseSuccess(registers);

    }

    //查询一个科室的所有挂号信息
    public JSONObject getAllDepartmentRegisters(Integer department_id) {
        List<Register> registers;
        try{
            registers  = registerMapper.getRegisterByDepartmentId(department_id);
            for(Register register: registers){
                register.setPatient(patientMapper.getPatientByRecordId(register.getRegister_info_id()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail(null);
        }

        return ConstantUtils.responseSuccess(registers);
    }

    //根据患者姓名和医生ID查询挂号信息
    public JSONObject getRegisterByDoctorIdAndPatientName(Integer doctor_id, String patient_name){
        List<Register> registers;
        try{
            registers = registerMapper.getRegisterByDoctorIdAndPatientName(doctor_id,patient_name);
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail(null);
        }
        return ConstantUtils.responseSuccess(registers);
    }


    //接诊
    public JSONObject treat(Integer register_id){
        try{
            doctorMapper.treat(register_id, ConstantDefinition.REGISTER_STATE[1]);
        }catch (Exception e){
            e.printStackTrace();
           return ConstantUtils.responseFail("接诊失败",null);
        }
        return ConstantUtils.responseSuccess(registerMapper.getRegister(register_id));
    }

    //填写门诊病历首页
    @Transactional
    public JSONObject submitRecord(Record record){
        try {
            String record_state = recordMapper.getRecordStateById(record.getRecord_id());
            if(record_state == null){
                recordMapper.addRecord(record);
                return ConstantUtils.responseSuccess(record);
            }
            if(record_state.equals(ConstantDefinition.RECORD_STATE[0])){
                recordMapper.updateRecord(record);
                return ConstantUtils.responseSuccess("更新成功",recordMapper.getRecordById(record.getRecord_id()));
            }
            else if(record_state.equals(ConstantDefinition.RECORD_STATE[1])){
                return ConstantUtils.responseFail("已提交最终诊断，不能再次添加诊断",null);
            }
            else if(record_state.equals(ConstantDefinition.RECORD_STATE[2])){
                return ConstantUtils.responseFail("已提交初步诊断，不能更改",null);
            }
            else {
                return ConstantUtils.responseFail("已提交最终诊断，不能更改",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("提交出错",null);
        }
    }

    //提交最终确诊结果
    @Transactional
    public boolean submitFirstDiagnose(List<Diagnose> diagnoses){
        try {
            for(int i = 0; i<diagnoses.size(); i++){
                diagnoses.get(i).setDiagnose_type(ConstantDefinition.DIAGNOSE_TYPE[0]);
                diagnoseMapper.addDiagnose(diagnoses.get(i));
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    //新增医技项目
    public boolean addMedicalSkill(MedicalSkill medicalSkill){
        try{
            medicalSkill.setMedical_skill_execute_state(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[0]);
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


    //获取病人所有收费项目
    public JSONArray getFeeRecords(Integer patient_record_id) {
        JSONArray data = new JSONArray();
        List<Register> registers = registerMapper.getRegistersByPatientId(patient_record_id);
        for(int i = 0; i<registers.size();i++){
            data.add(billMapper.getBillForOneRecord(registers.get(i).getRegister_info_id()));
        }
        return data;
    }

    public JSONObject addMedicalSkillResult(MedicalSkill medicalSkill){
        MedicalSkill medicalSkillBefore;
        try{
            medicalSkillBefore= medicalSkillMapper.getMedicalSkill(medicalSkill.getMedical_skill_id());
        }
        catch (Exception e){
            e.printStackTrace();
            return ConstantUtils.responseFail("获取医技项目失败",null);
        }
        if(medicalSkillBefore == null){
            return ConstantUtils.responseFail("无此医技项目",null);
        }

        medicalSkillBefore.setMedical_skill_execute_state(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[4]);
        medicalSkillBefore.setMedical_skill_result(medicalSkill.getMedical_skill_result());

        try{
            medicalSkillMapper.updateMedicalSkillResult(medicalSkillBefore);
        }catch (Exception e){
            return ConstantUtils.responseFail("结果添加失败",null);
        }

        return ConstantUtils.responseSuccess(medicalSkillBefore);

    }


}

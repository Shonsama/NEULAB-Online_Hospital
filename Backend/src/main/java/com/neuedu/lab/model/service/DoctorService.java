package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import static com.neuedu.lab.Utils.ConstantDefinition.*;
import static com.neuedu.lab.Utils.ConstantUtils.responseFail;
import static com.neuedu.lab.Utils.ConstantUtils.responseSuccess;

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

    @Resource
    private MedicineMapper medicineMapper;

    @Resource
    private MedicalSkillContentMapper medicalSkillContentMapper;


    //查询一个医生的所有挂号信息
    public JSONObject getAllRegisters(Integer doctor_id){
        JSONObject resultRegister = new JSONObject();
        List<Register> registers;
        List<Register> registersState0 = new ArrayList<>();
        List<Register> registersState1 = new ArrayList<>();
        List<Register> registersState2 = new ArrayList<>();
        List<Register> registersState3 = new ArrayList<>();


        try{
            registers  = registerMapper.getRegisterByDoctorId(doctor_id);
            for(Register register: registers){
                register.setPatient(patientMapper.getPatientByRecordId(register.getRegister_info_patient_id()));
                if(register.getRegister_info_state().equals(REGISTER_STATE[0])){
                    registersState0.add(register);
                }else if(register.getRegister_info_state().equals(REGISTER_STATE[1])){
                    registersState1.add(register);
                }else if(register.getRegister_info_state().equals(REGISTER_STATE[2])){
                    registersState2.add(register);
                }else if(register.getRegister_info_state().equals(REGISTER_STATE[3])){
                    registersState3.add(register);
                }else {
                    return ConstantUtils.responseFail();
                }

            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        resultRegister.put("YiGuaHao",registersState0);
        resultRegister.put("YiJiuZhen",registersState1);
        resultRegister.put("YiZhenBi",registersState2);
        resultRegister.put("YiTuiHao",registersState3);
        return responseSuccess(resultRegister);

    }

    //查询一个医生的所有就诊过的挂号信息，用于医生对患者的费用查询
    public JSONObject getTreatedRegisters(Integer doctor_id){
        List<Register> registers;
        try{
            registers  = registerMapper.getRegisterByDoctorId(doctor_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        Iterator<Register> iterator = registers.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getRegister_info_state().equals(REGISTER_STATE[0])){
                iterator.remove();
            }
        }

        for(Register register: registers){
            //填充病人
            try{
                register.setPatient(patientMapper.getPatientByRecordId(register.getRegister_info_patient_id()));
            }catch (RuntimeException e){
                e.printStackTrace();
                return responseFail("获取病人信息出错",null);
            }
        }

        return responseSuccess(registers);

    }
    //查询一个科室的所有挂号信息
    public JSONObject getAllDepartmentRegisters(String department_id) {
        JSONObject resultRegister = new JSONObject();
        List<Register> registers;
        List<Register> registersState0 = new ArrayList<>();
        List<Register> registersState1 = new ArrayList<>();
        List<Register> registersState2 = new ArrayList<>();
        List<Register> registersState3 = new ArrayList<>();

        try{
            registers  = registerMapper.getRegisterByDepartmentId(department_id);
            for(Register register: registers){
                register.setPatient(patientMapper.getPatientByRecordId(register.getRegister_info_patient_id()));
                if(register.getRegister_info_state().equals(REGISTER_STATE[0])){
                    registersState0.add(register);
                }else if(register.getRegister_info_state().equals(REGISTER_STATE[1])){
                    registersState1.add(register);
                }else if(register.getRegister_info_state().equals(REGISTER_STATE[2])){
                    registersState2.add(register);
                }else if(register.getRegister_info_state().equals(REGISTER_STATE[3])){
                    registersState3.add(register);
                }else {
                    return ConstantUtils.responseFail();
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(null);
        }
        resultRegister.put("YiGuaHao",registersState0);
        resultRegister.put("YiJiuZhen",registersState1);
        resultRegister.put("YiZhenBi",registersState2);
        resultRegister.put("YiTuiHao",registersState3);
        return responseSuccess(resultRegister);
    }

    //根据患者姓名和医生ID查询挂号信息
    public JSONObject getRegisterByDoctorIdAndPatientName(Integer doctor_id, String patient_name){
        List<Register> registers;
        try{
            registers = registerMapper.getRegisterByDoctorIdAndPatientName(doctor_id,patient_name);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(null);
        }
        return responseSuccess(registers);
    }


    //接诊
    public JSONObject treat(Integer register_id){
        try{
            doctorMapper.treat(register_id, REGISTER_STATE[1]);
        }catch (RuntimeException e){
            e.printStackTrace();
           return responseFail("接诊失败",null);
        }
        return responseSuccess(registerMapper.getRegister(register_id));
    }

    //填写门诊病历首页
    @Transactional
    public JSONObject submitRecord(Record record){
        try {
            String record_state = recordMapper.getRecordStateById(record.getRecord_id());
            if(record_state == null){
                record.setRecord_state(RECORD_STATE[0]);
                recordMapper.addRecord(record);
                return responseSuccess(record);
            }
            if(record_state.equals(RECORD_STATE[0])){
                recordMapper.updateRecord(record);
                return responseSuccess("更新成功",recordMapper.getRecordById(record.getRecord_id()));
            }
            else if(record_state.equals(RECORD_STATE[1])){
                return responseFail("已提交最终诊断，不能再次添加诊断",null);
            }
            else if(record_state.equals(RECORD_STATE[2])){
                return responseFail("已提交初步诊断，不能更改",null);
            }
            else {
                return responseFail("已提交最终诊断，不能更改",null);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("提交出错",null);
        }
    }

    //提交初步诊断结果
    @Transactional
    public JSONObject submitFirstDiagnose(List<Diagnose> diagnoses){
        try {
            if(diagnoses.size()==0){
                return ConstantUtils.responseFail("无诊断显示",null);
            }
            diagnoseMapper.deleteDiagnosesByRecordId(diagnoses.get(0).getDiagnose_record_id());
            for(int i = 0; i<diagnoses.size(); i++){
                diagnoses.get(i).setDiagnose_type(DIAGNOSE_TYPE[0]);
                diagnoseMapper.addDiagnose(diagnoses.get(i));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(null);
        }
        return responseSuccess(recordMapper.getRecordById(diagnoses.get(0).getDiagnose_record_id()));
    }

    //确认初诊完毕
    public JSONObject confirmFirstDiagnose(Integer record_id){
        try{
            recordMapper.updateRecordStateById(record_id,RECORD_STATE[1]);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(null);
        }
        return responseSuccess(recordMapper.getRecordById(record_id));
    }



    //新增医技项目
    public boolean addMedicalSkill(MedicalSkill medicalSkill){
        try{
            medicalSkill.setMedical_skill_execute_state(MEDICAL_SKILL_EXECUTE_STATE[0]);
            medicalSkillMapper.addMedicalSkill(medicalSkill);
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //删除医技项目
    public boolean deleteMedicalSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.deleteMedicalSkill(medical_skill_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //开立医技项目
    public boolean startMedicalSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.updateMedicalSkillState(medical_skill_id,MEDICAL_SKILL_EXECUTE_STATE[1],null);
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //作废医技项目
    public boolean endMedicalSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.updateMedicalSkillState(medical_skill_id, MEDICAL_SKILL_EXECUTE_STATE[2],null);
        }catch (RuntimeException e){
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
    public JSONObject getRecord(Integer record_id){
        //获取病历
        Record record;
        try{
             record = recordMapper.getRecordById(record_id);
            if(record == null){
                return responseFail("不存在此病历记录",null);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("获取病历过程出错",e);
        }
        //填充病历
        try{
            record.setFirstDiagnoses(diagnoseMapper.getDiagnoses(record_id, DIAGNOSE_TYPE[0]));
            record.setFinalDiagnoses(diagnoseMapper.getDiagnoses(record_id, DIAGNOSE_TYPE[1]));
            record.setMedicalSkills(medicalSkillMapper.getMedicalSkillByRegisterIdForDoctor(record_id));
            //填充处方
            record.setPrescriptions(fulfill( prescriptionMapper.getPrescriptionByRegisterId(record_id)));

            record.setRegister(registerMapper.getRegister(record_id));
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail("获取病历相关内容过程出错",e);
        }

        return responseSuccess(record);
    }

    //提交最终确诊结果
    @Transactional
    public JSONObject submitFinalDiagnose(List<Diagnose> diagnoses){
        try {
            for(int i = 0; i<diagnoses.size(); i++){
                diagnoses.get(i).setDiagnose_type(DIAGNOSE_TYPE[1]);
                diagnoseMapper.addDiagnose(diagnoses.get(i));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(getRecord(diagnoses.get(0).getDiagnose_record_id()));
    }

    //开立处方
    public JSONObject addPrescription(Prescription prescription){
        try{
            prescription.setPrescription_execute_state(PRESCRIPTION_EXECUTE_STATE[0]);
            prescription.setPrescription_fee(new BigDecimal(0));
            //更新处方名称为默认值
            if(prescription.getPrescription_name()==null){
                prescription.setPrescription_name(ADD_PRESCRIPTION_NAME+prescription.getPrescription_id());
                prescriptionMapper.updatePrescriptionName(prescription);
            }
            prescriptionMapper.addPrescription(prescription);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(prescriptionMapper.getPrescription(prescription.getPrescription_id()));
    }

    //删除处方
    @Transactional
    public JSONObject deletePrescription(Integer prescription_id){
        Prescription prescription;
        try{
//            prescriptionContentMapper.deletePrescriptionContents(prescription_id);
            //先确定处方存在和状态
            prescription = prescriptionMapper.getPrescription(prescription_id);
            if(prescription==null){
                return responseFail("处方不存在",null);
            }else if(!prescription.getPrescription_execute_state().equals(PRESCRIPTION_EXECUTE_STATE[0])){
                return responseFail("该处方状态为["+prescription.getPrescription_execute_state()+"],不可删除处方",prescription);
            }
            //先删除处方内容
            prescriptionContentMapper.deletePrescriptionContents(prescription_id);
            //再删除处方
            prescriptionMapper.deletePrescription(prescription_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(prescriptionMapper.getPrescription(prescription_id));
    }

    //发送处方,作废处方
    public JSONObject sendPrescription(Integer prescription_id) {
        try {
            prescriptionMapper.updatePrescriptionState(prescription_id, PRESCRIPTION_EXECUTE_STATE[1]);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(prescriptionMapper.getPrescription(prescription_id));
    }

    public JSONObject endPrescription(Integer prescription_id) {
        try {
            prescriptionMapper.updatePrescriptionState(prescription_id, PRESCRIPTION_EXECUTE_STATE[2]);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(prescriptionMapper.getPrescription(prescription_id));
    }


    //给某个处方增加药品
    public JSONObject addPrescriptionContent(PrescriptionContent prescriptionContent){
        try {
            //先通过药品id确定记录单价，总价
            Medicine medicine = medicineMapper.getMedicine(prescriptionContent.getPrescription_medicine_id());
            prescriptionContent.setPrescription_refund_available_num(prescriptionContent.getPrescription_num());
            prescriptionContent.setPrescription_unit_price(medicine.getMedicine_unit_price());
            prescriptionContent.setPrescription_content_fee(medicine.getMedicine_unit_price().multiply(new BigDecimal(prescriptionContent.getPrescription_num())));
            //增加药品
            prescriptionContentMapper.addPrescriptionContent(prescriptionContent);

            //更新处方费用
            Prescription prescription = prescriptionMapper.getPrescription(prescriptionContent.getPrescription_id());
            prescription.setPrescription_fee(prescription.getPrescription_fee().add(prescriptionContent.getPrescription_content_fee()));
            prescriptionMapper.updatePrescriptionFee(prescription);

        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(prescriptionMapper.getPrescription(prescriptionContent.getPrescription_id()));
        }
//        Prescription prescription = prescriptionMapper.getPrescription(prescriptionContent.getPrescription_id());
//        prescription.setPrescriptionContents(prescriptionContentMapper.getPrescriptionContentsPart(prescription.getPrescription_id()));
        return responseSuccess(prescriptionContent);
    }

    //给某个处方删除药品
    public JSONObject deletePrescriptionContent(Integer prescription_content_id) {
        Prescription prescription;
        try {
            PrescriptionContent prescriptionContent = prescriptionContentMapper.getPrescriptionContentById(prescription_content_id);
            prescriptionContentMapper.deletePrescriptionContent(prescription_content_id);

            //更新处方费用
            prescription = prescriptionMapper.getPrescription(prescriptionContent.getPrescription_id());
            prescription.setPrescription_fee(prescription.getPrescription_fee().subtract(prescriptionContent.getPrescription_content_fee()));
            prescriptionMapper.updatePrescriptionFee(prescription);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return responseFail();
        }
       return responseSuccess(fulfill(prescriptionMapper.getPrescription(prescription.getPrescription_id())));
    }


    //诊毕
    public JSONObject finish(Integer register_id){
        try{
            registerMapper.updateRegisterState(register_id, REGISTER_STATE[2]);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(getRecord(register_id));
    }


    //获取病人所有收费项目
    public JSONObject getFeeRecords(Integer register_id) {
        List<Bill> bills = new ArrayList<>();

        try{
            bills = billMapper.getBillForOneRecord(register_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            responseFail();
        }
        return responseSuccess(bills);
    }

    //获取一个病人的所有处方记录
    public JSONObject getPrescription(Integer register_id){
        List<Prescription> prescriptions;
        try{
            prescriptions = prescriptionMapper.getPrescriptionByRegisterId(register_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail(null);
        }
        return responseSuccess(fulfill(prescriptions));
    }

    //获取一个病人的所有医技记录
    public JSONObject getMedicalSkill(Integer register_id){
        List<MedicalSkill> medicalSkills;
        try{
            medicalSkills = medicalSkillMapper.getMedicalSkillsByRegisterId(register_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            return responseFail();
        }
        return responseSuccess(fulfillMS(medicalSkills));
    }


    //==================私有内部方法=================

    //获取一个处方的所有内容及药品
    private Prescription fulfill(Prescription prescription){
        List<PrescriptionContent> contentList;
        contentList = prescriptionContentMapper.getPrescriptionContents(prescription.getPrescription_id());
        for(PrescriptionContent prescriptionContent: contentList){
            prescriptionContent.setMedicine(medicineMapper.getMedicine(prescriptionContent.getPrescription_medicine_id()));
        }
        prescription.setPrescriptionContents(contentList);
        return prescription;
    }
    private List<Prescription> fulfill(List<Prescription> prescriptionList){
        List<Prescription> prescriptions = new ArrayList<>();
        for(Prescription prescription : prescriptionList){
            prescriptions.add(fulfill(prescription));
        }
        return prescriptions;
    }


    private MedicalSkill fulfillMS(MedicalSkill medicalSkill){
        MedicalSkillContent medicalSkillContent = medicalSkillContentMapper.getMedicalSkillContent(medicalSkill.getMedical_skill_content_id());
        medicalSkill.setMedicalSkillContent(medicalSkillContent);
        return medicalSkill;
    }
    private List<MedicalSkill> fulfillMS(List<MedicalSkill> medicalSkills){
        List<MedicalSkill> result = new ArrayList<>();
        for(MedicalSkill medicalSkill : medicalSkills){
            result.add(fulfillMS(medicalSkill));
        }
        return result;
    }


}

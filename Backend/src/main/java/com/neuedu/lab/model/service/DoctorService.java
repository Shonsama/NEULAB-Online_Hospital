package com.neuedu.lab.model.service;

import com.neuedu.lab.ConstantDefinition;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.MedicalSkill;
import com.neuedu.lab.model.po.Record;
import com.neuedu.lab.model.po.Register;
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


    //查询一个医生的所有挂号信息
    public List<Register> getAllRegisters(Integer doctor_id){
        return registerMapper.getRegisterByDoctorId(doctor_id);
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
            Boolean record_final_sumbit = recordMapper.getRecord(record.getRecord_id());
            if(record_final_sumbit== null){
                recordMapper.addRecord(record);
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
    public boolean addMedicialSkill(MedicalSkill medicalSkill){
        try{
            medicalSkillMapper.addMedicalSkill(medicalSkill);
            medicalSkill.setMedical_skill_excute_state(ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[0]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //删除医技项目
    public boolean deleteMedicialSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.deleteMedicalSkill(medical_skill_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //开立医技项目
    public boolean startMedicialSkill(Integer medical_skill_id){
        try{
            medicalSkillMapper.updateMedicalSkillState(medical_skill_id,ConstantDefinition.MEDICAL_SKILL_EXECUTE_STATE[1]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //作废医技项目
    public boolean endMedicialSkill(Integer medical_skill_id){
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



















}

package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.mapper.PatientMapper;
import com.neuedu.lab.model.po.Patient;
import com.neuedu.lab.model.po.PatientUser;
import com.neuedu.lab.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatientService {

    @Resource
    private PatientMapper patientMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    //private boolean ifSignUpSucceedPatient = false;

    public List<Patient> getAllPatients() {
        return patientMapper.getAllPatients();
    }

    public JSONObject addPatient(Patient patient) {
        try {
            patientMapper.addPatient(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ConstantUtils.responseFail();
        }
        return ConstantUtils.responseSuccess(patient);
    }

    public Patient getPatientByRecordId(Integer patient_id) {
        return patientMapper.getPatientByRecordId(patient_id);
    }

    public List<Patient> getPatientByDoctorId(String doctor_id) {
        return patientMapper.getPatientByDoctorId(doctor_id);
    }

    public List<Patient> getPatientByDepartmentId(String department_id) {
        return patientMapper.getPatientByDepartmentId(department_id);
    }

    public boolean checkIfExistAccount(String patient_account) {
        Integer size = patientMapper.getAllAccounts().size();
        Integer count = 0;
        for (int i = 0; i < size; i++) {
            if (!patientMapper.getAllAccounts().get(i).equals(patient_account)) {
                count++;
            }
        }
        if (count == size) {
            return false;//不存在
        } else
            return true;
    }

    public boolean checkIfExistId(Integer patient_record_id) {
        Integer size = patientMapper.getAllPatientRecordIds().size();
        for (int i = 0; i < size; i++) {
            if (patientMapper.getAllPatientRecordIds().get(i).equals(patient_record_id)) {
                return true;
            }
        }
        return false;
    }


    //登录
    public JSONObject checkUserValid(String patient_account, String patient_password) {
        if(!checkIfExistAccount(patient_account)){
            return ConstantUtils.responseFail("用户不存在");
        }else{
            //校验密码是否正确
            if(patientMapper.getPatientUserByAccount(patient_account).getPatient_password().equals(patient_password)){
                JSONObject result = new JSONObject();
                result.put("patient",patientMapper.getPatientUserByAccount(patient_account));
                result.put("token", ConstantUtils.generateToken(patient_account));
                return ConstantUtils.responseSuccess("登录成功",result);
            }else{
                return ConstantUtils.responseFail("密码错误");
            }
        }
    }

    //    注册用户
    public JSONObject signUp(String patient_account, String patient_password) {
        if (!checkIfExistAccount(patient_account)) {
            //able to sign up
            PatientUser patientUser = new PatientUser();
            patientUser.setPatient_account(patient_account);
            patientUser.setPatient_password(patient_password);
            patientMapper.addPatientUser(patientUser);
            //ifSignUpSucceedPatient = true;
            return ConstantUtils.responseSuccess("注册成功");
        } else {
            //ifSignUpSucceedPatient = false;
            return ConstantUtils.responseFail("注册失败，已有相同account存在");
        }
    }

    public JSONObject signUpWithRedis(String patient_account, String patient_password){
        while (redisTemplate.opsForHash().hasKey("functions", "addPatient")) {
            try {
                Thread.sleep(100);
                System.out.println("Someone is signing up!!!!!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        redisTemplate.opsForHash().put("functions", "addPatient", "whatever");
        JSONObject obj = signUp(patient_account,patient_password);
        redisTemplate.opsForHash().delete("functions", "addPatient");
        return obj;
    }

    //绑定已有病历号
    public JSONObject boundExistedRecord(String patient_account, Integer patient_record_id) {
        if (checkIfExistId(patient_record_id)) {//有该病历号
            patientMapper.updatePatientUserRecordId(patient_record_id,patient_account);
            return ConstantUtils.responseSuccess("绑定成功",patientMapper.getPatientByRecordId(patient_record_id));
        }else//无该病历号，无法绑定
            return ConstantUtils.responseFail("绑定失败");
    }

    //新建病历
    public JSONObject createNewRecord(Patient patient,String patient_account) {
        addPatient(patient);
        patientMapper.updatePatientUserRecordId(patient.getPatient_record_id(),patient_account);
        return ConstantUtils.responseSuccess("新建用户成功",patient);
    }

    public PatientUser getPatientUserById(Integer patient_user_id) {
        return patientMapper.getPatientUserById(patient_user_id);
    }

    public PatientUser getPatientUserByAccount(String patient_account) {
        return patientMapper.getPatientUserByAccount(patient_account);
    }

    public boolean updatePatientUser(PatientUser patientUser) {
        try {
            patientMapper.updatePatientUser(patientUser);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deletePatientUser(Integer patient_user_id) {
        try {
            patientMapper.deletePatientUser(patient_user_id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

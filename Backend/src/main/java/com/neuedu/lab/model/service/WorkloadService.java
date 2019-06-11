package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.DepartmentWorkloadMapper;
import com.neuedu.lab.model.mapper.DoctorWorkloadMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkloadService {
    @Resource
    private DoctorWorkloadMapper doctorWorkloadMapper;
    @Resource
    private DepartmentWorkloadMapper departmentWorkloadMapper;

    public Integer checkIfNull(Integer source) {
        if (source == null)
            return 0;
        else
            return source;
    }

    public BigDecimal checkIfNull(BigDecimal source) {
        if (source == null)
            return new BigDecimal(0);
        else
            return source;
    }

    public JSONObject getDoctorsWorkload(Date start_time, Date end_time) {
        List<JSONObject> list = new ArrayList<>();
        Integer doctorNum = doctorWorkloadMapper.getDoctors().size();
        for (int i = 0; i < doctorNum; i++) {
            JSONObject obj = new JSONObject();
            obj.put("getDoctorName", doctorWorkloadMapper.getDoctors().get(i).getDoctor_name());
            obj.put("getVisitorsNum", checkIfNull(doctorWorkloadMapper.getVisitorsNum(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getBillsNum", checkIfNull(doctorWorkloadMapper.getBillsNum(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getRegisterFee", checkIfNull(doctorWorkloadMapper.getRegisterFee(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getMedicalSkillCheck", checkIfNull(doctorWorkloadMapper.getMedicalSkillCheck(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getMedicalSkillInspect", checkIfNull(doctorWorkloadMapper.getMedicalSkillInspect(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getMedicalSkillDispose", checkIfNull(doctorWorkloadMapper.getMedicalSkillDispose(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getPrescriptionWest", checkIfNull(doctorWorkloadMapper.getPrescriptionWest(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getPrescriptionChinese", checkIfNull(doctorWorkloadMapper.getPrescriptionChinese(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            list.add(obj);
        }
        return ConstantUtils.responseSuccess(list);
    }

    public JSONObject getDepartmentsWorkloadDraw(Date start_time, Date end_time) {
        List<JSONObject> list = new ArrayList<>();
        Integer departmentNum = departmentWorkloadMapper.getDepartmentsClinical().size();
        for (int i = 0; i < departmentNum; i++) {
            JSONObject obj = new JSONObject();
            obj.put("getDepartmentName", departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_name());
            obj.put("getVisitorsNum", checkIfNull(departmentWorkloadMapper.getVisitorsNum(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getBillsNum", checkIfNull(departmentWorkloadMapper.getBillsNum(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getRegisterFee", checkIfNull(departmentWorkloadMapper.getRegisterFee(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getMedicalSkillCheck", checkIfNull(departmentWorkloadMapper.getMedicalSkillCheck(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getMedicalSkillInspect", checkIfNull(departmentWorkloadMapper.getMedicalSkillInspect(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getMedicalSkillDispose", checkIfNull(departmentWorkloadMapper.getMedicalSkillDispose(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getPrescriptionWest", checkIfNull(departmentWorkloadMapper.getPrescriptionWest(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getPrescriptionChinese", checkIfNull(departmentWorkloadMapper.getPrescriptionChinese(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            list.add(obj);
        }
        return ConstantUtils.responseSuccess(list);
    }

    public JSONObject getDepartmentsWorkloadExecute(Date start_time, Date end_time) {
        List<JSONObject> list = new ArrayList<>();
        Integer departmentNum = departmentWorkloadMapper.getDepartmentsMedicalSkill().size();
        for (int i = 0; i < departmentNum; i++) {
            JSONObject obj = new JSONObject();
            obj.put("getDepartmentName", departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_name());
            obj.put("getVisitorsNum", checkIfNull(departmentWorkloadMapper.getVisitorsNum(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getBillsNum", checkIfNull(departmentWorkloadMapper.getBillsNum(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getRegisterFee", checkIfNull(departmentWorkloadMapper.getRegisterFee(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getMedicalSkillCheck", checkIfNull(departmentWorkloadMapper.getMedicalSkillCheck(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getMedicalSkillInspect", checkIfNull(departmentWorkloadMapper.getMedicalSkillInspect(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getMedicalSkillDispose", checkIfNull(departmentWorkloadMapper.getMedicalSkillDispose(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getPrescriptionWest", checkIfNull(departmentWorkloadMapper.getPrescriptionWest(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getPrescriptionChinese", checkIfNull(departmentWorkloadMapper.getPrescriptionChinese(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            list.add(obj);
        }
        return ConstantUtils.responseSuccess(list);
    }
}
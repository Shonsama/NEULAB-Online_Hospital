package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.mapper.DepartmentWorkloadMapper;
import com.neuedu.lab.model.mapper.DoctorWorkloadMapper;
import com.neuedu.lab.utils.ConstantUtils;
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

    public JSONObject getDoctorsWorkload(Date start_time, Date end_time){
        long startTime=System.currentTimeMillis();
        List<JSONObject> list = new ArrayList<>();
        Integer doctorNum = doctorWorkloadMapper.getDoctors().size();
        for (int i = 0; i < doctorNum; i++) {
            JSONObject obj = new JSONObject();
            obj.put("getDoctorName", doctorWorkloadMapper.getDoctors().get(i).getDoctor_name());
            long startTime1=System.currentTimeMillis();
            obj.put("getVisitorsNum", checkIfNull(doctorWorkloadMapper.getVisitorsNum(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime1=System.currentTimeMillis();

            long startTime2=System.currentTimeMillis();
            //obj.put("getBillsNum", checkIfNull(doctorWorkloadMapper.getBillsNumInRegister(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())
            //+doctorWorkloadMapper.getBillsNumInMedicalSkill(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())
            //+doctorWorkloadMapper.getBillsNumInPrescription(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            obj.put("getBillsNum", checkIfNull(doctorWorkloadMapper.getBillsNum(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime2=System.currentTimeMillis();

            long startTime3=System.currentTimeMillis();
            obj.put("getRegisterFee", checkIfNull(doctorWorkloadMapper.getRegisterFee(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime3=System.currentTimeMillis();
            long startTime4=System.currentTimeMillis();
            obj.put("getMedicalSkillCheck", checkIfNull(doctorWorkloadMapper.getMedicalSkillCheck(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime4=System.currentTimeMillis();
            long startTime5=System.currentTimeMillis();
            obj.put("getMedicalSkillInspect", checkIfNull(doctorWorkloadMapper.getMedicalSkillInspect(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime5=System.currentTimeMillis();
            long startTime6=System.currentTimeMillis();
            obj.put("getMedicalSkillDispose", checkIfNull(doctorWorkloadMapper.getMedicalSkillDispose(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime6=System.currentTimeMillis();
            long startTime7=System.currentTimeMillis();
            obj.put("getPrescriptionWest", checkIfNull(doctorWorkloadMapper.getPrescriptionWest(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime7=System.currentTimeMillis();
            long startTime8=System.currentTimeMillis();
            obj.put("getPrescriptionChinese", checkIfNull(doctorWorkloadMapper.getPrescriptionChinese(start_time, end_time, doctorWorkloadMapper.getDoctors().get(i).getDoctor_id())));
            long endTime8=System.currentTimeMillis();
            list.add(obj);
            System.out.println("doctor"+i+":");
            System.out.println("getVisitorsNum:"+(endTime1 - startTime1)+"ms");
            System.out.println("getBillsNum:"+(endTime2 - startTime2)+"ms");
            System.out.println("getRegisterFee:"+(endTime3 - startTime3)+"ms");
            System.out.println("getMedicalSkillCheck:"+(endTime4 - startTime4)+"ms");
            System.out.println("getMedicalSkillInspect:"+(endTime5 - startTime5)+"ms");
            System.out.println("getMedicalSkillDispose:"+(endTime6 - startTime6)+"ms");
            System.out.println("getPrescriptionWest:"+(endTime7 - startTime7)+"ms");
            System.out.println("getPrescriptionChinese:"+(endTime8 - startTime8)+"ms");
            System.out.println("Process time for current doctor:"+(endTime8 - startTime1)+"ms");
        }
        long endTime=System.currentTimeMillis();
        System.out.println("Process time for all doctors:"+(endTime - startTime)+"ms");
        return ConstantUtils.responseSuccess(list);
    }

    public JSONObject getDepartmentsWorkloadDraw(Date start_time, Date end_time) {
        List<JSONObject> list = new ArrayList<>();
        Integer departmentNum = departmentWorkloadMapper.getDepartmentsClinical().size();
        for (int i = 0; i < departmentNum; i++) {
            JSONObject obj = new JSONObject();
            obj.put("getDepartmentName", departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_name());
            obj.put("getVisitorsNum", checkIfNull(departmentWorkloadMapper.getVisitorsNum(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            //obj.put("getBillsNum", checkIfNull(departmentWorkloadMapper.getBillsNum(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
            obj.put("getBillsNum", checkIfNull(departmentWorkloadMapper.getBillsNumInRegister(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())+
                            departmentWorkloadMapper.getBillsNumInMedicalSkill(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())+
                            departmentWorkloadMapper.getBillsNumInPrescription(start_time, end_time, departmentWorkloadMapper.getDepartmentsClinical().get(i).getDepartment_id())));
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
            //obj.put("getBillsNum", checkIfNull(departmentWorkloadMapper.getBillsNum(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
            obj.put("getBillsNum", checkIfNull(departmentWorkloadMapper.getBillsNumInRegister(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())+
                    departmentWorkloadMapper.getBillsNumInMedicalSkill(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())+
                    departmentWorkloadMapper.getBillsNumInPrescription(start_time, end_time, departmentWorkloadMapper.getDepartmentsMedicalSkill().get(i).getDepartment_id())));
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
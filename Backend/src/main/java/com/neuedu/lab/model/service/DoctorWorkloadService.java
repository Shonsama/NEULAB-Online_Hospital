package com.neuedu.lab.model.service;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Utils.ConstantUtils;
import com.neuedu.lab.model.mapper.DoctorWorkloadMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DoctorWorkloadService {
    @Resource
    private DoctorWorkloadMapper doctorWorkloadMapper;

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
        Integer doctorNum = doctorWorkloadMapper.getDoctorsId().size();
        for (int i = 0; i < doctorNum; i++) {
            JSONObject obj = new JSONObject();
            obj.put("getVisitorsNum", checkIfNull(doctorWorkloadMapper.getVisitorsNum(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            obj.put("getBillsNum", checkIfNull(doctorWorkloadMapper.getBillsNum(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            obj.put("getRegisterFee", checkIfNull(doctorWorkloadMapper.getRegisterFee(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            obj.put("getMedicalSkillCheck", checkIfNull(doctorWorkloadMapper.getMedicalSkillCheck(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            obj.put("getMedicalSkillInspect", checkIfNull(doctorWorkloadMapper.getMedicalSkillInspect(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            obj.put("getMedicalSkillDispose", checkIfNull(doctorWorkloadMapper.getMedicalSkillDispose(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            obj.put("getPrescriptionWest", checkIfNull(doctorWorkloadMapper.getPrescriptionWest(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            obj.put("getPrescriptionChinese", checkIfNull(doctorWorkloadMapper.getPrescriptionChinese(start_time, end_time, doctorWorkloadMapper.getDoctorsId().get(i))));
            list.add(obj);
        }
        return ConstantUtils.responseSuccess(list);
    }
}
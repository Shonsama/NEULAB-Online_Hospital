package com.neuedu.lab.model.service;

import com.neuedu.lab.ConstantDefinition;
import com.neuedu.lab.model.mapper.*;
import com.neuedu.lab.model.po.Constant;
import com.neuedu.lab.model.po.Record;
import com.neuedu.lab.model.po.Register;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DoctorService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private RegisterMapper registerMapper;
    @Resource
    private BillMapper billMapper;
    @Resource
    private RecordMapper recordMapper;

    public List<Register> getAllRegisters(Integer doctor_id){
        return registerMapper.getRegisterByDoctorId(doctor_id);
    }

    public List<Register> getRegisterByDoctorIdAndPatientName(Integer doctor_id, String patient_name){
        return registerMapper.getRegisterByDoctorIdAndPatientName(doctor_id,patient_name);
    }


    public boolean treat(Integer register_id){
        try{
            doctorMapper.treat(register_id, ConstantDefinition.REGISTER_STATE[1]);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


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






}

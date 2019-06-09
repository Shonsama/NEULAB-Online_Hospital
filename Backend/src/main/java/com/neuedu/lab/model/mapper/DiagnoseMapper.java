package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Diagnose;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiagnoseMapper {
    void addDiagnose(Diagnose diagnose);

    //供查询患者病历使用
    List<Diagnose> getDiagnoses (@Param("diagnose_record_id") Integer diagnose_record_id, @Param("diagnose_type") String diagnose_type);

    //供医生删除原诊断使用
    void deleteDiagnosesByRecordId(Integer diagnose_record_id);
}

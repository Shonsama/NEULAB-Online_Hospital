package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BillMapper {
    public void addBill(Bill bill);

    public Bill getBill(Integer register_id);

    List<Bill> getBillForOneRecord(Integer register_id);

    List<Bill> getBillByUserIdAndTime(@Param("bill_user_id") Integer bill_user_id,
                                      @Param("start_time") Date start_time,
                                      @Param("end_time") Date end_time);

    //供退费使用
    List<Bill> getBillByMedicalSkillId(Integer bill_medical_skill_id);

    Bill getBillByPrescriptionId(Integer bill_prescription_id);

    void updateBillNum(String bill_num,Integer bill_id);

    //日结冻结发票
    void updateBillFrozen(@Param("bill_user_id") Integer bill_user_id,
                          @Param("start_time") Date start_time,
                          @Param("end_time") Date end_time);
}

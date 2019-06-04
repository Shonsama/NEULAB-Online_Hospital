package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Bill;

import java.util.List;

public interface BillMapper {
    public void addBill(Bill bill);

    public Bill getBill(Integer register_id);

    List<Bill> getBillForOneRecord(Integer register_id);
}

package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Bill;

public interface BillMapper {
    public void addBill(Bill bill);

    public Bill getBill(Integer register_id);
}

package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping("/reprint")
    public JSONObject rePrintBill(@RequestBody JSONObject request){
        return billService.rePrintBill(request.getInteger("bill_id"));
    }

    @RequestMapping("/overprint")
    public JSONObject overPrintBill(@RequestBody JSONObject request){
        return billService.overPrintBill(request.getInteger("bill_id"));
    }

}

package com.neuedu.lab.Utils;

import java.util.Date;

public class ConstantDefinition {

    public static final String URL_BASE_FRONT = "http://";
    public static final boolean DEBUG_MODE = true;
    public static final String[] REGISTER_STATE = {"已挂号", "已就诊", "诊毕", "已退号"};
    public static final String[] MEDICAL_SKILL_EXECUTE_STATE = {"已添加", "已开立", "已作废", "已缴费", "已消费", "已退费","已确认执行","已取消执行"};
    public static final String[] DIAGNOSE_TYPE = {"初步诊断", "最终诊断"};
    public static final String[] RECORD_STATE = {"已暂存","已提交初步诊断","已提交最终诊断"};
    public static final String[] PRESCRIPTION_EXECUTE_STATE = {"已暂存", "已发送", "已作废", "已缴费", "已领药", "已退药","已退费","已删除"};
    //只有暂存状态下的处方是可以修改或删除的,其他状态不可以
    //开立之后的处方是不能被删除的，可以点击“作废”，作废处方。作废处方是指收款人员页面看不到处方信息，也不能进行划价收款。

    public static final String[] DAILY_PASS_STATE = {"未确认提交","已确认提交","审核通过","审核未通过"};

    public static final String[] BILL_STATE = {"正常","作废","重打","补打","对冲"};

    public static final String ADD_PRESCRIPTION_NAME = "新增处方";

    public static final String SUCCESS_MESSAGE = "SUCCESS";



    public static final String FAIL_MESSAGE = "FAIL";
    public static final String FAIL_INSERT_MESSAGE = "插入失败";



    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 500;


    public static final String[] BILL_TYPE = {"挂号费", "检查费", "检验费", "处置费","中药费","西药费"};
    public static final String[] REFUND_TYPE = {"检查","检验","处置","中药","西药"};
    //Token失效时间
    public static final Long EXPIRY_TIME = 2000 * 3600L;  //过期时间2小时

    public static final String [] USER_TYPE = {"user","doctor"};

}

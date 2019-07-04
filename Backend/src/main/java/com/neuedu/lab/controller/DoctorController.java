package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.*;
import com.neuedu.lab.model.service.DoctorService;
import com.neuedu.lab.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 给医生的接口
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Qualifier("asyncServiceExecutor")
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping("/get-all-registers")
    public JSONObject getAllRegisters(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.getAllRegisters(request.getInteger("doctor_id"));
        });
        return future.get() ;
    }

    @RequestMapping("/get-doctor-treated-registers")
    public JSONObject getTreatedRegisters(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.getTreatedRegisters(request.getInteger("doctor_id"));
        });
        return future.get() ;
    }

    @RequestMapping("/get-department-registers")
    public JSONObject getDepartmentRegisters(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.getAllDepartmentRegisters(request.getString("department_id"));
        });
        return future.get() ;
    }

    //接诊
    @RequestMapping("/treat")
    public JSONObject treat( @RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        //接诊病人
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.treat(request.getInteger("register_id"));
        });
        return future.get() ;
    }

    //医生根据患者姓名查询挂号信息
    @RequestMapping("/get-register")
    public JSONObject getRegisterByDoctorIdAndPatientName(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return ConstantUtils.responseSuccess(doctorService.getRegisterByDoctorIdAndPatientName(request.getInteger("doctor_id"),request.getString("patient_name")));
        });
        return future.get() ;
    }

    //提交诊断
    @RequestMapping("/submit-record")
    public JSONObject submitRecord(@RequestBody Record record) throws ExecutionException, InterruptedException {

        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.submitRecord(record);
        });
        return future.get() ;
    }

    //提交初步诊断
    @RequestMapping("/submit-first-diagnose")
    public JSONObject submitFirstDiagnose(@RequestBody List<Diagnose> diagnoses) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.submitFirstDiagnose(diagnoses);
        });
        return future.get() ;
    }

    //确认初步诊断
    @RequestMapping("/confirm-first-diagnose")
    public JSONObject confirmFirstDiagnose(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.confirmFirstDiagnose(request.getInteger("record_id"));
        });
        return future.get() ;
    }

    //添加医技项目
    @RequestMapping("/add-medical-skill")
    public JSONObject addMedicalSkill(@RequestBody MedicalSkill medicalSkill) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.addMedicalSkill(medicalSkill);
        });
        return future.get() ;
    }

    //删除医技项目
    @RequestMapping("/delete-medical-skill")
    public JSONObject deleteMedicalSkill(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.deleteMedicalSkill(request.getInteger("medical_skill_id"));
        });
        return future.get() ;
    }

    //开立医技项目
    @RequestMapping("/start-medical-skill")
    public JSONObject startMedicalSkill(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.startMedicalSkill(request.getInteger("medical_skill_id"));
        });
        return future.get() ;

    }

    //作废医技项目
    @RequestMapping("/end-medical-skill")
    public JSONObject endMedicalSkill(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.endMedicalSkill(request.getInteger("medical_skill_id"));
        });
        return future.get() ;
    }

    @RequestMapping("/check-result")
    public JSONObject checkResult(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.checkResult(request.getInteger("medical_skill_id"));
        });
        return future.get() ;
    }

    //查看初步诊断
    @RequestMapping("/get-record")
    public JSONObject getRecord(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return  doctorService.getRecord(request.getInteger("record_id"));
        });
        return future.get() ;
    }

    //提交最终诊断
    @RequestMapping("/submit-final-diagnose")
    public JSONObject submitFinalDiagnose(@RequestBody List<Diagnose> diagnoses) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.submitFinalDiagnose(diagnoses);
        });
        return future.get() ;
    }

    //新增处方
    @RequestMapping("/add-prescription")
    public JSONObject addPrescription(@RequestBody Prescription prescription) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.addPrescription(prescription);
        });
        return future.get() ;
    }

    //删除处方
    @RequestMapping("/delete-prescription")
    public JSONObject deletePrescription(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.deletePrescription(request.getInteger("prescription_id"));
        });
        return future.get() ;
    }

    //发送处方、作废处方
        @RequestMapping("/send-prescription")
    public JSONObject sendPrescription(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
            Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
                // logger.info("执行线程！");
                return doctorService.sendPrescription(request.getInteger("prescription_id"));
            });
            return future.get() ;
    }
    @RequestMapping("/end-prescription")
    public JSONObject endPrescription(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.endPrescription(request.getInteger("prescription_id"));
        });
        return future.get() ;
    }



    //增加药品
    @RequestMapping("/add-medicine")
    public JSONObject addMedicine(@RequestBody PrescriptionContent prescriptionContent) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.addPrescriptionContent(prescriptionContent);
        });
        return future.get() ;
    }

    //删除药品
    @RequestMapping("/delete-medicine")
    public JSONObject deleteMedicine(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.deletePrescriptionContent(request.getInteger("prescription_content_id"));
        });
        return future.get() ;
    }


    //诊毕
    @RequestMapping("/finish")
    public JSONObject finish(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.finish(request.getInteger("register_id"));
        });
        return future.get() ;
    }

    //查询患者本次看病费用记录
    @RequestMapping("/get-fee-records")
    public JSONObject fee(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.getFeeRecords(request.getInteger("register_id"));
        });
        return future.get() ;
    }

    //获取病人的所有处方
    @RequestMapping("/get-all-prescription")
    public JSONObject getPrescription(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.getPrescription(request.getInteger("register_id"));
        });
        return future.get() ;
    }

    //获取病人的所有医技
    @RequestMapping("/get-all-medical-skill")
    public JSONObject getMedicalSkill(@RequestBody JSONObject request) throws ExecutionException, InterruptedException {
        Future<JSONObject> future = threadPoolTaskExecutor.submit(() -> {
            // logger.info("执行线程！");
            return doctorService.getMedicalSkill(request.getInteger("register_id"));
        });
        return future.get() ;
    }
}

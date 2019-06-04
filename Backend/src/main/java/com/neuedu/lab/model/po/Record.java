package com.neuedu.lab.model.po;

import java.util.List;

public class Record {
    private Integer record_patient_id;
    private Integer record_doctor_id;
    private Integer record_id;
    private String record_syndrome;
    private String record_health_check;
    private String record_xianbingshi;
    private String record_jiwangshi;
    private String record_cure_situation;
    private String record_allergy_his;
    private boolean record_final_submit;

    public boolean getRecord_final_submit() {
        return record_final_submit;
    }

    public void setRecord_final_submit(boolean record_final_submit) {
        this.record_final_submit = record_final_submit;
    }

    public List<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    private List<Diagnose> diagnoses;

    public Integer getRecord_patient_id() {
        return record_patient_id;
    }

    public void setRecord_patient_id(Integer record_patient_id) {
        this.record_patient_id = record_patient_id;
    }

    public Integer getRecord_doctor_id() {
        return record_doctor_id;
    }

    public void setRecord_doctor_id(Integer record_doctor_id) {
        this.record_doctor_id = record_doctor_id;
    }

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }


    public String getRecord_syndrome() {
        return record_syndrome;
    }

    public void setRecord_syndrome(String record_syndrome) {
        this.record_syndrome = record_syndrome;
    }

    public String getRecord_health_check() {
        return record_health_check;
    }

    public void setRecord_health_check(String record_health_check) {
        this.record_health_check = record_health_check;
    }

    public String getRecord_xianbingshi() {
        return record_xianbingshi;
    }

    public void setRecord_xianbingshi(String record_xianbingshi) {
        this.record_xianbingshi = record_xianbingshi;
    }

    public String getRecord_jiwangshi() {
        return record_jiwangshi;
    }

    public void setRecord_jiwangshi(String record_jiwangshi) {
        this.record_jiwangshi = record_jiwangshi;
    }

    public String getRecord_cure_situation() {
        return record_cure_situation;
    }

    public void setRecord_cure_situation(String record_cure_situation) {
        this.record_cure_situation = record_cure_situation;
    }

    public String getRecord_allergy_his() {
        return record_allergy_his;
    }

    public void setRecord_allergy_his(String record_allergy_his) {
        this.record_allergy_his = record_allergy_his;
    }
}

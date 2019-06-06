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
    private String record_state;
    private String record_suggestion;
    private String record_attention;
    private String record_doctor_type;
    private List<Diagnose> diagnoses;


    public String getRecord_state() {
        return record_state;
    }

    public void setRecord_state(String record_state) {
        this.record_state = record_state;
    }

    public String getRecord_doctor_type() {
        return record_doctor_type;
    }

    public void setRecord_doctor_type(String record_doctor_type) {
        this.record_doctor_type = record_doctor_type;
    }

    public String getRecord_suggestion() {
        return record_suggestion;
    }

    public void setRecord_suggestion(String record_suggestion) {
        this.record_suggestion = record_suggestion;
    }

    public String getRecord_attention() {
        return record_attention;
    }

    public void setRecord_attention(String record_attention) {
        this.record_attention = record_attention;
    }



    public List<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }


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

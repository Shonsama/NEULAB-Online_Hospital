package com.neuedu.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.model.po.*;
import com.neuedu.lab.model.service.*;
import com.neuedu.lab.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//用于各个基本表的增删改查
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    //常量表的增删改查
    @Autowired
    private ConstantService constantService;

    @RequestMapping("constant/get-all")
    public JSONObject getAllConstant(){
        return ConstantUtils.responseSuccess(constantService.getAllConstant());
    }


    @RequestMapping("constant/get")
    public Object getConstant(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(constantService.getConstant(request.getString("constant_type")));
    }

    @RequestMapping("constant/add")
    public JSONObject addConstant(@RequestBody Constant constant){
        if(constantService.addConstant(constant)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("constant/update")
    public JSONObject updateConstant(@RequestBody Constant constant){
        if(constantService.updateConstant(constant)){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("constant/delete")
    public JSONObject deleteConstant(@RequestBody JSONObject request){
        if(constantService.deleteConstant(request.getInteger("constant_id"))){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //部门的增删改查
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("department/get-all")
//    显示所有科室信息
    public JSONObject getAllDepartments(){
        return ConstantUtils.responseSuccess(departmentService.getAllDepartments());
    }

    @RequestMapping("department/get")
//    显示查询科室信息
    public JSONObject getDepartment(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(departmentService.getDepartment(request.getString("department_id")));
    }

    @RequestMapping("department/add")
    public JSONObject addDepartment(@RequestBody Department department){
        if(departmentService.addDepartment(department)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("department/update")
    public JSONObject updateDepartment(@RequestBody Department department){
        if(departmentService.updateDepartment(department)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("department/delete")
    public JSONObject deleteDepartment(@RequestBody JSONObject department_id){
        String getString = department_id.getString("department_id");
        if(departmentService.deleteDepartment(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }


    //疾病信息表
    @Autowired
    private DiseaseInfoService diseaseInfoService;

    @RequestMapping("disease/get-all")
//    显示所有疾病信息
    public JSONObject getAllDiseases(){
        return ConstantUtils.responseSuccess(diseaseInfoService.getAllDiseases());
    }

    @RequestMapping("disease/get")
//    显示查询疾病信息
    public JSONObject getDisease(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(diseaseInfoService.getDisease(request.getString("disease_id")));
    }

    @RequestMapping("disease/add")
    public JSONObject addDisease(@RequestBody DiseaseInfo diseaseInfo){
        if(diseaseInfoService.addDisease(diseaseInfo)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("disease/update")
    public JSONObject updateDisease(@RequestBody DiseaseInfo diseaseInfo){
        if(diseaseInfoService.updateDisease(diseaseInfo)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("disease/delete")
    public JSONObject deleteDisease(@RequestBody JSONObject disease_id){
        String getString = disease_id.getString("disease_id");
        if(diseaseInfoService.deleteDisease(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //收费目录表

    @Autowired
    private FeeCatService feeCatService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @RequestMapping("fee-cat/get")
    public Object getFeeCat(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(feeCatService.getFeeCat(request.getString("fee_cat_id")));
    }

    @RequestMapping("fee-cat/get-all")
    public JSONObject getAllFeeCats(){
        return ConstantUtils.responseSuccess(feeCatService.getAllFeeCats());
    }

    @RequestMapping("fee-cat/add")
    public JSONObject addFeeCat(@RequestBody FeeCat feeCat){
        if(feeCatService.addFeeCat(feeCat)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("fee-cat/update")
    public JSONObject updateFeeCat(@RequestBody FeeCat feeCat){
        if(feeCatService.updateFeeCat(feeCat)){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("fee-cat/delete")
    public JSONObject deleteFeeCat(@RequestBody JSONObject request){
        if(feeCatService.deleteFeeCat(request.getString("fee_cat_id"))){
            return ConstantUtils.responseSuccess(null);        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //医技内容表

    @Autowired
    private MedicalSkillContentService medicalSkillContentService;

    @RequestMapping("medical-skill-content/get-all")
//    显示所有科室信息
    public JSONObject getAllMedicalSkillContents(){
        return ConstantUtils.responseSuccess(medicalSkillContentService.getAllMedicalSkillContents());
    }

    @RequestMapping("medical-skill-content/get")
//    显示查询科室信息
    public JSONObject getMedicalSkillContent(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(medicalSkillContentService.getMedicalSkillContent(request.getString("medical_skill_content_id")));
    }

    @RequestMapping("medical-skill-content/add")
    public JSONObject addMedicalSkillContent(@RequestBody MedicalSkillContent medicalSkillContent){
        if(medicalSkillContentService.addMedicalSkillContent(medicalSkillContent)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("medical-skill-content/update")
    public JSONObject updateMedicalSkillContent(@RequestBody MedicalSkillContent medicalSkillContent){
        if(medicalSkillContentService.updateMedicalSkillContent(medicalSkillContent)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("medical-skill-content/delete")
    public JSONObject deleteMedicalSkillContent(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(medicalSkillContentService.deleteMedicalSkillContent(request.getString("medical_skill_content_id")));
    }

    //药品信息表

    @Autowired
    private MedicineService medicineService;

    @RequestMapping("medicine/get-all")
//    返回所有药品信息
    public JSONObject getAllMedicines(){
        return ConstantUtils.responseSuccess(medicineService.getAllMedicines());
    }

    @RequestMapping("medicine/get")
//    查询药品信息
    public JSONObject getMedicine(@RequestBody JSONObject medicine_id){
        String getString = medicine_id.getString("medicine_id");
        return ConstantUtils.responseSuccess(medicineService.getMedicine(getString));
    }

    @RequestMapping("medicine/add")
    public JSONObject addMedicine(@RequestBody Medicine medicine){
        if(medicineService.addMedicine(medicine)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("medicine/update")
    public JSONObject updateMedicine(@RequestBody Medicine medicine){
        if(medicineService.updateMedicine(medicine)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("medicine/delete")
    public JSONObject deleteMedicine(@RequestBody JSONObject medicine_id){
        String getString = medicine_id.getString("medicine_id");
        if(medicineService.deleteMedicine(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //非药品目录表
    @Autowired
    private NonMedicineService nonMedicineService;

    @RequestMapping("non-medicine/get-all")
//    返回所有非药品信息
    public JSONObject getAllNonMedicines(){
        return ConstantUtils.responseSuccess(nonMedicineService.getAllNonMedicines());
    }

    @RequestMapping("non-medicine/get")
//    查询非药品信息
    public JSONObject getNonMedicine(@RequestBody JSONObject non_medicine_id){
        String getString = non_medicine_id.getString("non_medicine_id");
        return ConstantUtils.responseSuccess(nonMedicineService.getNonMedicine(getString));
    }

    @RequestMapping("non-medicine/add")
    public JSONObject addNonMedicine(@RequestBody NonMedicine nonMedicine){
        if(nonMedicineService.addNonMedicine(nonMedicine)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("non-medicine/update")
    public JSONObject updateNonMedicine(@RequestBody NonMedicine nonMedicine){
        if(nonMedicineService.updateNonMedicine(nonMedicine)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("non-medicine/delete")
    public JSONObject deleteNonMedicine(@RequestBody JSONObject non_medicine_id){
        String getString = non_medicine_id.getString("non_medicine_id");
        if(nonMedicineService.deleteNonMedicine(getString)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    //挂号级别表
    @Autowired
    private RegisterLevelService registerLevelService;
    @RequestMapping("register-level/get-all")
    public JSONObject getAllRegisterLevels(){
        return ConstantUtils.responseSuccess(registerLevelService.getAllRegisterLevels());
    }
    @RequestMapping("register-level/get")
//    显示查询科室信息
    public JSONObject getRegisterLevel(@RequestBody JSONObject request){
        return ConstantUtils.responseSuccess(registerLevelService.getRegisterLevel(request.getInteger("register_level_id")));
    }

    @RequestMapping("register-level/add")
    public JSONObject addRegisterLevel(@RequestBody RegisterLevel registerLevel){
        if(registerLevelService.addRegisterLevel(registerLevel)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("register-level/update")
    public JSONObject updateRegisterLevel(@RequestBody RegisterLevel registerLevel){
        if(registerLevelService.updateRegisterLevel(registerLevel)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }

    @RequestMapping("register-level/delete")
    public JSONObject deleteRegisterLevel(@RequestBody JSONObject request){
        Integer getInteger = request.getInteger("register_level_id");
        if(registerLevelService.deleteRegisterLevel(getInteger)){
            return ConstantUtils.responseSuccess(null);
        }
        else {
            return ConstantUtils.responseFail(null);
        }
    }


}

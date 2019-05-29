# Neuedu_lab
本项目为项目实训的项目代码
## 1.基础信息维护
### 科室管理：
    post:''
    return:
        data:[{
        id,//科室编码
        name,//科室名称
        classification,//科室分类
        type//科室类别
        }]
### 挂号级别管理：
    attribute:id,level,fee,sequence,maximum
    post:''
    return:
        data:[{
        id,//科室编码
        level,//号别名称
        fee,//费用
        sequence,//顺序号
        maximum//挂号限额
        }]
### 常数类别管理
    attribute:id,name,category
    post:''
    return:
        data:[{
        id,//常量ID
        name,//常量名称
        category,//常量类别
        }]
### 诊断项目管理
    attribute:id,name,category,ICD
    post:''
    return:
        data:[{
        id,//疾病编码
        name,//疾病名称
        ICD,//国际ICD编码
        category,//疾病所属分类
        }]
### 非药品收费项目管理
    attribute:id,name,category,ICD
    post:''
    return:
        data:[{
        id,//项目编码
        name,//项目名称
        standard,//规格
        price_category,//费用分类
        price,//单价
        }]
## 2.门诊挂号收费


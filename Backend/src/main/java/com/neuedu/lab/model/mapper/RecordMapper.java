package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Record;

public interface RecordMapper {
    public void addRecord(Record record);

    public void updateRecord(Record record);

    public String getRecordStateById (Integer record_id);

    public Record getRecordById(Integer record_id);


}

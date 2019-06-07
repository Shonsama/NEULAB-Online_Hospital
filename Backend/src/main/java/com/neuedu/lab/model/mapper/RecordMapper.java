package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Record;
import org.apache.ibatis.annotations.Param;

public interface RecordMapper {
    public void addRecord(Record record);

    public void updateRecord(Record record);

    public String getRecordStateById (Integer record_id);

    public Record getRecordById(Integer record_id);

    void updateRecordStateById(@Param("record_id") Integer record_id, @Param("record_state") String record_state);
}

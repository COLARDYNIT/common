package org.hwbot.api.bench.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import org.hwbot.api.bench.dto.DeviceRecordDTO.RecordType;

/**
 * For (android) benchmark app
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DeviceRecordsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int applicationId;
    private String application;

    private Map<RecordType, DeviceRecordDTO> records = new HashMap<DeviceRecordDTO.RecordType, DeviceRecordDTO>();

    public Map<RecordType, DeviceRecordDTO> getRecords() {
        return records;
    }

    public void setRecords(Map<RecordType, DeviceRecordDTO> records) {
        this.records = records;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "DeviceRecordsDTO [applicationId=" + applicationId + ", " + (application != null ? "application=" + application + ", " : "")
                + (records != null ? "records=" + records : "") + "]";
    }

}

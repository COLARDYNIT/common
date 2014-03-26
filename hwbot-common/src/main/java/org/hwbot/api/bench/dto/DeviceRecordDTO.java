package org.hwbot.api.bench.dto;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DeviceRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum RecordType {
        best_device, best_cpu_core, best_cpu_family, best_architecture
    };

    public RecordType type;
    public Float score;
    public Integer submissionId;
    public String user;

    public DeviceRecordDTO() {
        super();
    }

    public DeviceRecordDTO(RecordType type, Float score, Integer submissionId, String user) {
        super();
        this.type = type;
        this.score = score;
        this.submissionId = submissionId;
        this.user = user;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DeviceRecordDTO [" + (type != null ? "type=" + type + ", " : "") + (score != null ? "score=" + score + ", " : "")
                + (submissionId != null ? "submissionId=" + submissionId + ", " : "") + (user != null ? "user=" + user : "") + "]";
    }

}

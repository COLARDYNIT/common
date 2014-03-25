package org.hwbot.api.bench.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DeviceInfoDTO {

    private Integer id;
    private Integer soCid;
    private Integer videocardId;
    private String processor;
    private Integer processorId;
    private Integer processorCoreId;
    private Integer processorSubFamilyId;
    private Integer processorFamilyId;
    private String processorCore;
    private String processorSubFamily;
    private String processorFamily;
    private Integer processorClock;
    private String videocard;
    private Integer ram;
    private final String name;

    public DeviceInfoDTO(Integer id, String name, Integer soCid, Integer processorId, Integer videocardId, String processor, Integer processorClock,
            String videocard, Integer ram) {
        super();
        this.id = id;
        this.name = name;
        this.soCid = soCid;
        this.processorId = processorId;
        this.videocardId = videocardId;
        this.processor = processor;
        this.processorClock = processorClock;
        this.videocard = videocard;
        this.ram = ram;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoCid() {
        return soCid;
    }

    public void setSoCid(Integer soCid) {
        this.soCid = soCid;
    }

    public Integer getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Integer processorId) {
        this.processorId = processorId;
    }

    public Integer getVideocardId() {
        return videocardId;
    }

    public void setVideocardId(Integer videocardId) {
        this.videocardId = videocardId;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Integer getProcessorClock() {
        return processorClock;
    }

    public void setProcessorClock(Integer processorClock) {
        this.processorClock = processorClock;
    }

    public String getVideocard() {
        return videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getName() {
        return name;
    }

    public Integer getProcessorCoreId() {
        return processorCoreId;
    }

    public void setProcessorCoreId(Integer processorCoreId) {
        this.processorCoreId = processorCoreId;
    }

    public Integer getProcessorSubFamilyId() {
        return processorSubFamilyId;
    }

    public void setProcessorSubFamilyId(Integer processorSubFamilyId) {
        this.processorSubFamilyId = processorSubFamilyId;
    }

    public Integer getProcessorFamilyId() {
        return processorFamilyId;
    }

    public void setProcessorFamilyId(Integer processorFamilyId) {
        this.processorFamilyId = processorFamilyId;
    }

    public String getProcessorCore() {
        return processorCore;
    }

    public void setProcessorCore(String processorCore) {
        this.processorCore = processorCore;
    }

    public String getProcessorSubFamily() {
        return processorSubFamily;
    }

    public void setProcessorSubFamily(String processorSubFamily) {
        this.processorSubFamily = processorSubFamily;
    }

    public String getProcessorFamily() {
        return processorFamily;
    }

    public void setProcessorFamily(String processorFamily) {
        this.processorFamily = processorFamily;
    }

}

package org.hwbot.api.bench.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

/**
 * For (android) benchmark app
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class NotificationsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<NotificationDTO> list = new ArrayList<NotificationDTO>();

    public List<NotificationDTO> getList() {
        return list;
    }

    public void setList(List<NotificationDTO> list) {
        this.list = list;
    }

}

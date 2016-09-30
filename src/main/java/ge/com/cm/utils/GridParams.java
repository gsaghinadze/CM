/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.utils;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Giorgi
 */
public class GridParams implements Serializable {
    
    private Integer start;
    private Integer limit;
    private String filterRouteDesc;
    private Integer filterCarId;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") Date filterDate;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getFilterRouteDesc() {
        return filterRouteDesc;
    }

    public void setFilterRouteDesc(String filterRouteDesc) {
        this.filterRouteDesc = filterRouteDesc;
    }

    public Integer getFilterCarId() {
        return filterCarId;
    }

    public void setFilterCarId(Integer filterCarId) {
        this.filterCarId = filterCarId;
    }

    public Date getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(Date filterDate) {
        this.filterDate = filterDate;
    }

    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.com.cm.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author Giorgi
 */
@Audited
@Entity
@Table(name = "CARS")
public class Car extends BaseEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "CAR_NUMBER", nullable = false, unique = true)
    private String carNumber;

    @Column(name = "CAR_MODEL", nullable = true)
    private String carModel;

    @Column(name = "DATE_ISSUED", nullable = true)
    private Integer dateIssued;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Integer dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

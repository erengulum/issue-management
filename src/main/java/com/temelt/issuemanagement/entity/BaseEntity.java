package com.temelt.issuemanagement.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

//Base entity will be used for base properties that will be used by other Entity classes
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at") //We have to specify Column name even if it is a baseEntity class.These columns will be added to all entities that extends this class
    private Date createdAt;

    @Column(name = "created_by",length = 50)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by",length = 50)
    private String updatedBy;

    @Column(name = "status")
    private Boolean status;
}

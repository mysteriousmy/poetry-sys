package com.poetry.db.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "poetry_sys_dynasty")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysDynasty {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false, name = "dynasty_id")
    public String dynastyId;

    @Column(length = 50, nullable = false)
    public String dynastyName;

    @Column(length = 255)
    public String dynastyDesc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    public Date updateTime;
}

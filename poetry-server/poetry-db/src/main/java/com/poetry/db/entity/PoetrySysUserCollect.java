package com.poetry.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "poetry_sys_user_collects")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysUserCollect{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false)
    public String userCollectId;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "collect_userId", referencedColumnName = "user_id")
    @JsonIgnoreProperties(value = "collectList")
    public PoetrySysUsers sysUsers;

    @Column(length = 1, nullable = false)
    public Integer collectType;

    @Column(length = 64, nullable = false)
    public String collectId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Date updateTime;

}

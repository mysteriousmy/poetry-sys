package com.poetry.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "poetry_sys_search_history")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysSearchHistory {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false, name = "search_id")
    public String searchId;

    @Column(length = 255, nullable = false)
    public String searchWord;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "search_userId", referencedColumnName = "user_id")
    @JsonIgnoreProperties(value = "searchHistoryList")
    public PoetrySysUsers sysUsers;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Date updateTime;
}

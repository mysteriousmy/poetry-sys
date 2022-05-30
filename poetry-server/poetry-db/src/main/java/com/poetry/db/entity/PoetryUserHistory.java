package com.poetry.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "poetry_sys_users_history")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetryUserHistory {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false)
    public String userHistoryId;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "history_userId", referencedColumnName = "user_id")
    @JsonIgnoreProperties(value = "historyList")
    public PoetrySysUsers hisUsers;

    @Column(length = 1, nullable = false)
    public Integer historyType;

    @Column(length = 64, nullable = false)
    public String historyId;

}

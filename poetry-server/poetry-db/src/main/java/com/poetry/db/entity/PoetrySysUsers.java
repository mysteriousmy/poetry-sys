package com.poetry.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poetry.common.consts.enums.UsersGender;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "poetry_sys_users")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysUsers{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false, name = "user_id")
    public String userId;

    @Column(length = 64, nullable = false)
    public String openId;

    @Column(length = 50, nullable = false)
    public String userName;

    @Lob
    public String userPhoto;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public UsersGender userGender;

    @OneToMany(mappedBy = "sysUsers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<PoetrySysUserCollect> collectList;
    @OneToMany(mappedBy = "hisUsers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<PoetryUserHistory> historyList;
    @OneToMany(mappedBy = "sysUsers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<PoetrySysSearchHistory> searchHistoryList;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    public Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    public Date updateTime;


}

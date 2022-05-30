package com.poetry.db.entity;

import com.poetry.common.consts.enums.AdminsType;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "poetry_sys_admins")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysAdmins{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false)
    public String adminsId;
    @Column(length = 30, nullable = false)
    public String adminsName;
    @Column(length = 255, nullable = false)
    public String adminsPassword;
    @Column(length = 255, nullable = false)
    public String adminsEmail;
    @Column(length = 1, nullable = false, columnDefinition="tinyint default 1")
    @Enumerated(EnumType.ORDINAL)
    public AdminsType adminsType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Date updateTime;
}

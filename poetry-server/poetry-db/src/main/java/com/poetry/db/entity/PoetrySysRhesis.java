package com.poetry.db.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "poetry_sys_rhesis")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysRhesis{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false)
    public String rhesisId;

    @Lob
    @Column(nullable = false)
    public String rhesisContent;

    @Lob
    public String rhesisBackground;

    @JoinColumn(name="poems_id")
    @ManyToOne
    public PoetrySysPoems poems;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    public Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    public Date updateTime;
}

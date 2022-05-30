package com.poetry.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "poetry_sys_poets")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysPoets{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false, name = "poets_id")
    public String poetsId;

    @Column(length = 255, nullable = false)
    public String poetsName;

    @Lob
    public String poetsDesc;

    @Lob
    public String poetsPhoto;

    @JoinColumn(name = "dynasty_id")
    @ManyToOne
    public PoetrySysDynasty dynasty;

    @OneToMany(mappedBy = "poets", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<PoetrySysPoems> poems;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    public Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    public Date updateTime;

}

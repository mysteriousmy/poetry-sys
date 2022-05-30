package com.poetry.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poetry.common.consts.enums.PoemsType;
import com.poetry.common.consts.enums.UsersGender;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "poetry_sys_poems")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysPoems {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false, name = "poem_id")
    public String poemId;

    @Lob
    @Column(nullable = false)
    public String poemTitle;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public PoemsType poemsType;

    @Lob
    @Column(nullable = false)
    public String poemContent;

    @Lob
    public String poemTranslates;
    @Lob
    public String poemAnnotation;
    @Lob
    public String poemAppreciation;
    @Lob
    public String poemCreateScene;

    @JoinColumn(name = "dynasty_id")
    @ManyToOne
    public PoetrySysDynasty dynasty;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="poets_id")
    @JsonIgnoreProperties(value = "poems")
    public PoetrySysPoets poets;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    public Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    public Date updateTime;

}

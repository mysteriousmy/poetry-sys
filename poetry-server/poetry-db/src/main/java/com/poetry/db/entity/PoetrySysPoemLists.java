package com.poetry.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "poetry_sys_poem_lists")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PoetrySysPoemLists{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64, nullable = false, name = "poem_list_id")
    public String poemListId;
    @Lob
    @Column(nullable = false)
    public String poemListTitle;

    @Lob
    @Column(nullable = false)
    public String poemListDesc;
    @Lob
    @Column(nullable = false)
    public String poemListImage;

    @ManyToMany(targetEntity = PoetrySysPoems.class,cascade = CascadeType.ALL)
    @JoinTable(name="poetry_sys_poems_poemLists",//中间表的名称
            joinColumns={@JoinColumn(name="poem_list_id",referencedColumnName="poem_list_id")},
            inverseJoinColumns={@JoinColumn(name="poem_id",referencedColumnName="poem_id")}
    )
    public Set<PoetrySysPoems> poems;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable =false)
    public Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    public Date updateTime;

}

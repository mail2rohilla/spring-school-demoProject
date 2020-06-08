package com.deepanshu.entity;

import com.deepanshu.utility.UtilityJsonObject;
import com.deepanshu.utility.UtilityMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "school")
public class School {

    @Autowired
    transient ObjectMapper objectMapper;

    public School() {
        setId(null);
    }
    @Id
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
    private String id;

    @Column(name = "schoolName")
    private String schoolName;

    @Column(name = "demo")
    private String demo;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="schoolOne",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Student> studentList;

    @Access(AccessType.PROPERTY)
    @Column(name="meta")
    private String meta;

    @Transient
    public UtilityJsonObject metaData;


    public String getMeta() {

        if(this.metaData == null){
            return this.meta;
        }
        else{
            return this.metaData.toString();
        }
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public UtilityJsonObject getMetaData() {

        UtilityJsonObject utilityJsonObject;
        // when metaData is asked by the class then it should return the
        // existing state of meta to an object if object is already null
        if(this.metaData == null && (this.meta != null && this.meta.length() > 0))
            utilityJsonObject =  new UtilityJsonObject(this.meta);
        else if(this.meta == null)
            utilityJsonObject = new UtilityJsonObject("{}");
        else
            utilityJsonObject = this.metaData;

        setMetaData(utilityJsonObject);
        return utilityJsonObject;
    }

    public void setMetaData(UtilityJsonObject metaData) {
        this.metaData = metaData;
    }



    public String getId() {
        return id;
    }

    private void setId(String id) {
        if(id != null)
            this.id = id;
        else
            this.id = UtilityMethods.UUIDGenerator();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }
}

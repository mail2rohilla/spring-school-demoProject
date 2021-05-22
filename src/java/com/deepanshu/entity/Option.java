package com.deepanshu.entity;
import com.deepanshu.utility.UtilityJsonObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "optionValue")
public class Option extends EntityBase{



    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    String text;
    String type;
    String description;


    @Access(AccessType.PROPERTY)
    @Column(name="meta")
    private String meta;

    @Transient
    public UtilityJsonObject metaData;


    /////////////////////////// getters and setters /////////////////////////////////


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}

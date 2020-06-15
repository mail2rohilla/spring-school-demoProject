package com.deepanshu.entity;

import com.deepanshu.utility.UtilityJsonObject;
import com.deepanshu.utility.UtilityMethods;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "questionnaire")
public class Questionnaire extends EntityBase{

    String name;
    String subject;
    String type;
    String identifier;
    String description;

    // todo : convert to fetchType lazy
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="author")
    User author;
    String totalScore;


    public Questionnaire() {
        setId(null);
    }


    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Access(AccessType.PROPERTY)
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
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
}

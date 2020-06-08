package com.deepanshu.entity;

import com.deepanshu.utility.UtilityJsonObject;
import com.deepanshu.utility.UtilityMethods;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends EntityBase{

    public User() {
        setId(null);
    }

    @Id
    @Access(AccessType.PROPERTY)
    private String id;

    @Access(AccessType.PROPERTY)
    private String meta;

    @Transient
    public UtilityJsonObject metaData;


    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phoneNumber;
    private String type;
    private boolean accountExpired = false;
    private int passwordRetriesLeft;
    private String username;
    private String password;
    private boolean passwordExpired = false;
    private String status;





    ///////////////////////////////// getters and setters /////////////////////////////////////////



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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public int getPasswordRetriesLeft() {
        return passwordRetriesLeft;
    }

    public void setPasswordRetriesLeft(int passwordRetriesLeft) {
        this.passwordRetriesLeft = passwordRetriesLeft;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

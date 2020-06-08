package com.deepanshu.entity;

import com.deepanshu.utility.UtilityMethods;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "student")
public class Student implements Serializable{

        Student(){
                setId(null);
        }

        @Id
        @Column(name = "id")
        private String id;

        @Column(name = "firstName")
        private String fname;

        @Column(name = "lastName")
        private String lname;

        @Column(name = "email")
        private String email;

        @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
        @JoinColumn(name="school")
        private School schoolOne;


        public String getId() {
                return id;
        }

        public void setId(String id) {
                if(this.id == null){
                        this.id = UtilityMethods.UUIDGenerator();
                }else{
                        this.id = id;
                }
        }

        public String getFname() {
                System.out.println("deepanshu in the getter " + fname);
                return fname;
        }

        public String getLname() {
                return lname;
        }

        public String getEmail() {
                return email;
        }

        public void setFname(String fname) {
                this.fname = fname;
        }

        public void setLname(String lname) {
                this.lname = lname;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public School getSchoolOne() {
                return schoolOne;
        }

        public void setSchoolOne(School schoolOne) {
                this.schoolOne = schoolOne;
        }
}


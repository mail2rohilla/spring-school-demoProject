package com.deepanshu.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "student")
public class Student implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "first_name")
        private String fname;

        @Column(name = "last_name")
        private String lname;

        @Column(name = "email")
        private String email;

        // add new field for instructor (also add getter/setters)

        // add @OneToOne annotation


        public String getFname() {
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

}


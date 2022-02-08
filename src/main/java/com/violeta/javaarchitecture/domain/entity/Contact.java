package com.violeta.javaarchitecture.domain.entity;

import com.violeta.javaarchitecture.config.persistance.base.EntityType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@Table(name = "Contact")
public class Contact extends EntityType {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Must be a valid email")
    @Column(name = "email", nullable = false)
    private String email;

}
package com.internship.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"user\"")
// please notice that "user" table is already reserved as Postgres table, so we have too use ""
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Each user will be given an auto-generated unique identifier when stored

    @Column(name = "pass_hash", nullable = false, length = 10485760)
    private String passHash;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "user", targetEntity = UserCommunityEntity.class, fetch = FetchType.EAGER)
    private List<UserCommunityEntity> listOfUserCommunities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public List<UserCommunityEntity> getListOfUserCommunities() {
        return listOfUserCommunities;
    }

    public void setListOfUserCommunities(List<UserCommunityEntity> listOfUserCommunities) {
        this.listOfUserCommunities = listOfUserCommunities;
    }
}
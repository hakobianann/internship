package com.internship.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "community")
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Each user will be given an auto-generated unique identifier when stored

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "community", targetEntity = UserCommunityEntity.class)
    private List<UserCommunityEntity> listOfUserCommunities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserCommunityEntity> getListOfUserCommunities() {
        return listOfUserCommunities;
    }

    public void setListOfUserCommunities(List<UserCommunityEntity> listOfUserCommunities) {
        this.listOfUserCommunities = listOfUserCommunities;
    }
}
package com.internship.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_community")
public class UserCommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    private CommunityEntity community;

}
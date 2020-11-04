package com.example.application.views.helloworld1;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class FavoritesBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fbookId;



}

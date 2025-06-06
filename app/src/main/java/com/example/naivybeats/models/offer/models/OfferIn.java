package com.example.naivybeats.models.offer.models;

import android.hardware.camera2.params.LensIntrinsicsSample;

import com.example.naivybeats.models.style.model.Style;

import java.util.List;

public class OfferIn {
    private Integer offer_in_id;
    private String publish_date;
    private int salary;
    private String event_date;
    private Integer music_id_final;
    private int restaurant_id;
    private String description;
    private Integer done;
    private List<Integer> styles_ids;

    public OfferIn() {
    }

    public OfferIn(Integer offer_in_id, String publish_date, int salary, String event_date, Integer music_id_final, int restaurant_id, String description, Integer done, List<Integer> styles_ids) {
        this.offer_in_id = offer_in_id;
        this.publish_date = publish_date;
        this.salary = salary;
        this.event_date = event_date;
        this.music_id_final = music_id_final;
        this.restaurant_id = restaurant_id;
        this.description = description;
        this.done = done;
        this.styles_ids = styles_ids;
    }

    public int getOffer_in_id() {
        return offer_in_id;
    }

    public void setOffer_in_id(int offer_in_id) {
        this.offer_in_id = offer_in_id;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public void setMusic_id_final(Integer music_id_final) {
        this.music_id_final = music_id_final;
    }

    public Integer getMusic_id_final() {
        return music_id_final;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public List<Integer> getStyles_ids() {
        return styles_ids;
    }

    public void setStyles_ids(List<Integer> styles_ids) {
        this.styles_ids = styles_ids;
    }
}

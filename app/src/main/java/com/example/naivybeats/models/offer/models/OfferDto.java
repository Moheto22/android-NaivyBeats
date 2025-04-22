package com.example.naivybeats.models.offer.models;

import java.util.List;

public class OfferDto extends OfferIn{
    int postulated;

    public OfferDto() {
    }

    public OfferDto(int offer_in_id, String publish_date, int salary, String event_date, Integer music_id_final, int restaurant_id, String description, Integer done, List<Integer> styles_ids, int postulated) {
        super(offer_in_id, publish_date, salary, event_date, music_id_final, restaurant_id, description, done, styles_ids);
        this.postulated = postulated;
    }

    public int getPostulated() {
        return postulated;
    }

    public void setPostulated(int postulated) {
        this.postulated = postulated;
    }
}

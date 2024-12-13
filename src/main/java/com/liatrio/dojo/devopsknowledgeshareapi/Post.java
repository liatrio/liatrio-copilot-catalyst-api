package com.liatrio.dojo.devopsknowledgeshareapi;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

import java.util.*;
import java.text.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue
    private Long objectId;
    private @NonNull String firstName;
    private @NonNull String title;
    private @NonNull String link;
    private @NonNull String datePosted;
    private @NonNull String imageUrl;
    private @NonNull Date dateAsDate;

    private String dateUpdated;

    public void setDateUpdated(Date dateAsDate) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormat());
        this.dateUpdated = dateFormat.format(dateAsDate);
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public Post() {
        this.dateAsDate = Calendar.getInstance().getTime();
        setDatePosted(dateAsDate);
    }

    public Long getId() {
        return objectId;
    }

    public void setId(Long objectId) {
        this.objectId = objectId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) throws Exception {
        if (this.validatePostLink(link)) {
            this.link = link;
        } else {
            throw new Exception("Invalid link format ");
        }
    }

    public String getDatePosted() {
        return datePosted;
    }

    public String dateFormat() {
        return "EE MM/dd/yyyy H:m:s:S z";
    }

    public void setDatePosted(Date dateAsDate) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormat());
        this.datePosted = dateFormat.format(dateAsDate);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean validatePostLink(String postLink) {
        String pattern = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        return postLink.matches(pattern);
    }
}

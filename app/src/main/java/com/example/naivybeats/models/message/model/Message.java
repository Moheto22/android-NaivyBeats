package com.example.naivybeats.models.message.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Message
{
    @SerializedName("message_id")
    Integer messageId;
    @SerializedName("text")
    String text;
    @SerializedName("publish_date")
    String publishDate;
    @SerializedName("reading_date")
    String readingDate;
    @SerializedName("chat_id")
    int chatId;
    @SerializedName("user_id")
    int userId;
    @SerializedName("offer")
    Integer offer;
    @SerializedName("accept")
    Integer accept;

    public Message()
    {
    }

    public Message(Integer messageId, String readingDate, int chatId, int userId, String publishDate, String text, Integer offer, Integer accept)
    {
        this.messageId = messageId;
        this.readingDate = readingDate;
        this.chatId = chatId;
        this.userId = userId;
        this.publishDate = publishDate;
        this.text = text;
        this.offer = offer;
        this.accept = accept;
    }

    public int getMessageId()
    {
        return messageId;
    }

    public void setMessageId(int messageId)
    {
        this.messageId = messageId;
    }
    public Integer getOffer()
    {
        return offer;
    }
    public void setAccept(int accept){
        this.accept = accept;
    }

    public Integer getAccept() {
        return accept;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(String publishDate)
    {
        this.publishDate = publishDate;
    }

    public String getReadingDate()
    {
        return readingDate;
    }

    public void setReadingDate(String readingDate)
    {
        this.readingDate = readingDate;
    }

    public int getChatId()
    {
        return chatId;
    }

    public void setChatId(int chatId)
    {
        this.chatId = chatId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}

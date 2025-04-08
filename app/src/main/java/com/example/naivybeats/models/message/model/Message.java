package com.example.naivybeats.models.message.model;

import java.sql.Date;

public class Message
{
    int messageId;
    String text;
    Date publishDate;
    Date readingDate;
    int chatId;
    int userId;

    public Message()
    {
    }

    public Message(int messageId, Date readingDate, int chatId, int userId, Date publishDate, String text)
    {
        this.messageId = messageId;
        this.readingDate = readingDate;
        this.chatId = chatId;
        this.userId = userId;
        this.publishDate = publishDate;
        this.text = text;
    }

    public int getMessageId()
    {
        return messageId;
    }

    public void setMessageId(int messageId)
    {
        this.messageId = messageId;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Date getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
    }

    public Date getReadingDate()
    {
        return readingDate;
    }

    public void setReadingDate(Date readingDate)
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

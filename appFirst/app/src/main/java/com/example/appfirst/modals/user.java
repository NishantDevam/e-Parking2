package com.example.appfirst.modals;

public class user {

    String profilePic,userName,mailId,password,userId,lastMessage,status;

    public user(String profilePic, String userName, String mailId, String password, String userId, String lastMessage,String status) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mailId = mailId;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.status=status;
    }

    public user(String s, String toString, String string){}


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}

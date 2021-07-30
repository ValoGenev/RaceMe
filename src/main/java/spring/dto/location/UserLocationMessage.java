package spring.dto.location;

import spring.entity.UserEntity;

public class UserLocationMessage {

    private String id;
    private String latitude;
    private String longitude;
    private UserEntity user;

    public UserLocationMessage(String id, String latitude, String longitude, UserEntity user) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }

    public UserLocationMessage(String id, String latitude, String longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserLocationMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserLocationMessage{" +
                "id='" + id + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", user=" + user +
                '}';
    }
}

package spring.dto.facebook;

public class FacebookUserDto {

    private String name;
    private String email;
    private FacebookPictureDto picture;

    public FacebookUserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FacebookPictureDto getPicture() {
        return picture;
    }

    public void setPicture(FacebookPictureDto picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "FacebookUserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture=" + picture +
                '}';
    }
}

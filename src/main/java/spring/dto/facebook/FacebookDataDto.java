package spring.dto.facebook;

public class FacebookDataDto {

    private String url;

    public FacebookDataDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FacebookDataDto{" +
                "url='" + url + '\'' +
                '}';
    }
}

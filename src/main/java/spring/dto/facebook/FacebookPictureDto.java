package spring.dto.facebook;

public class FacebookPictureDto {

    private FacebookDataDto data;

    public FacebookPictureDto() {
    }

    public FacebookPictureDto(FacebookDataDto data) {
        this.data = data;
    }


    public FacebookDataDto getData() {
        return data;
    }

    public void setData(FacebookDataDto data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "FacebookPictureDto{" +
                "data=" + data +
                '}';
    }
}

package spring.dto.github;

public class GitHubEmailsResponseDto {

    private String email;
    private boolean primary;

    public GitHubEmailsResponseDto(String email, boolean primary) {
        this.email = email;
        this.primary = primary;
    }

    public GitHubEmailsResponseDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    public String toString() {
        return "GitHubEmailsResponseDto{" +
                "email='" + email + '\'' +
                ", primary=" + primary +
                '}';
    }
}

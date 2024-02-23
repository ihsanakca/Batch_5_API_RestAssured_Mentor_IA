package apiPOJOTemplates.devEx;

public class RegisterNewUser {
    /**
     * {
     *   "email": "string",
     *   "password": "string",
     *   "name": "string",
     *   "google": "string",
     *   "facebook": "string",
     *   "github": "string"
     * }
     */

    private String email;
    private String github;
    private String facebook;
    private String google;
    private String name;
    private String password;


    public RegisterNewUser() {
    }

    public RegisterNewUser(String email, String github, String facebook, String google, String name, String password) {
        this.email = email;
        this.github = github;
        this.facebook = facebook;
        this.google = google;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterNewUser{" +
                "email='" + email + '\'' +
                ", github='" + github + '\'' +
                ", facebook='" + facebook + '\'' +
                ", google='" + google + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

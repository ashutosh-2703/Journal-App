package net.engineeringdigest.journalApp.dto;


public class LoginResponse {
    
    private String token;
    private String tokenType = "Bearer";
    private String userName;

    public LoginResponse() {
    }

    public LoginResponse(String token, String tokenType, String userName) {
        this.token = token;
        this.tokenType = tokenType;
        this.userName = userName;
    }

    // Builder pattern (manually implemented)
    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public static class LoginResponseBuilder {
        private String token;
        private String tokenType = "Bearer";
        private String userName;

        public LoginResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public LoginResponseBuilder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public LoginResponseBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(token, tokenType, userName);
        }
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

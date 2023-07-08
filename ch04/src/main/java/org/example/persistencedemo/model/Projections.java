package org.example.persistencedemo.model;

import org.springframework.beans.factory.annotation.Value;

public class Projections {

    public interface UserSummary {
        String getUsername();

        @Value("#{target.username} #{target.email}")
        String getInfo();
    }

    public static class UsernameOnly {
        private String username;

        public UsernameOnly(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}

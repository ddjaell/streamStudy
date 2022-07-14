package pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserWithTraditionalBuilder {
    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private List<Integer> friendUserIdList;

    public UserWithTraditionalBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.isVerified = builder.isVerified;
        this.friendUserIdList = builder.friendUserIdList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", isVerified=" + isVerified +
                ", friendUserIdList=" + friendUserIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWithTraditionalBuilder user = (UserWithTraditionalBuilder) o;
        return id == user.id && isVerified == user.isVerified && Objects.equals(name, user.name) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(friendUserIdList, user.friendUserIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, emailAddress, isVerified, friendUserIdList);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(emailAddress);
    }

    public boolean isVerified() {
        return isVerified;
    }

    public List<Integer> getFriendUserIdList() {
        return friendUserIdList;
    }

    public static Builder builder(int id, String name) {
        return new Builder(id, name);
    }

    public static class Builder {
        private int id;
        private String name;
        private String emailAddress;
        private boolean isVerified;
        private List<Integer> friendUserIdList = new ArrayList<>();

        private Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }
        public Builder verified(boolean verified) {
            this.isVerified = verified;
            return this;
        }
        public Builder friendUserIdList(List<Integer> friendUserIdList) {
            this.friendUserIdList = friendUserIdList;
            return this;

        }

        public UserWithTraditionalBuilder build() {
            return new UserWithTraditionalBuilder(this);
        }
    }
}

package pojo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class UserWithFunctionBuilder {
    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private List<Integer> friendUserIdList;

    public UserWithFunctionBuilder(Builder builder) {
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
        UserWithFunctionBuilder user = (UserWithFunctionBuilder) o;
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
        public String emailAddress;
        public boolean isVerified;
        public List<Integer> friendUserIdList;

        private Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder with(Consumer<Builder> consumer) {
            consumer.accept(this);
            return this;
        }

        public UserWithFunctionBuilder build() {
            return new UserWithFunctionBuilder(this);
        }
    }
}

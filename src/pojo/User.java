package pojo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class User {
    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private List<Integer> friendUserIdList;

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
        User user = (User) o;
        return id == user.id && isVerified == user.isVerified && Objects.equals(name, user.name) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(friendUserIdList, user.friendUserIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, emailAddress, isVerified, friendUserIdList);
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public User setVerified(boolean verified) {
        isVerified = verified;
        return this;
    }

    public User setFriendUserIdList(List<Integer> friendUserIdList) {
        this.friendUserIdList = friendUserIdList;
        return this;
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
}

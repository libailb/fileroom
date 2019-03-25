package libai.aliyun.fileroom.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`fileuser`")
public class UserEntity implements Serializable {
    @Id
    @Column(name = "`id`")
    private Float id;

    @Column(name = "`username`")
    private String username;

    @Column(name = "`password`")
    private String password;

    /**
     * @return id
     */
    public Float getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Float id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}
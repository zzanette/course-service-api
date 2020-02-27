package com.ilog.course.model.user;

import com.ilog.course.model.BaseModel;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseModel implements UserDetails {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 60)
  @Column(name = "full_name")
  private String fullName;

  @NotBlank
  @Size(max = 100)
  @Column(name = "email")
  private String email;

  @NotBlank
  @Size(max = 50)
  @Column(name = "username")
  private String username;

  @NotBlank
  @Size(max = 255)
  @Column(name = "password")
  private String password;

  @NotNull
  @Column(name = "is_account_non_expired")
  private Boolean isAccountNonExpired;

  @NotNull
  @Column(name = "is_account_non_locked")
  private Boolean isAccountNonLocked;

  @NotNull
  @Column(name = "is_credentials_non_expired")
  private Boolean isCredentialsNonExpired;

  @NotNull
  @Column(name = "is_enabled")
  private Boolean isEnabled;

  public User(String fullName, String email, String username, String password) {
    this.fullName = fullName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.isAccountNonExpired = Boolean.TRUE;
    this.isAccountNonLocked = Boolean.FALSE;
    this.isCredentialsNonExpired = Boolean.TRUE;
    this.isEnabled = Boolean.TRUE;
    this.isValid();
  }

  public User unLockAccount() {
    this.isAccountNonLocked = Boolean.TRUE;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }
}

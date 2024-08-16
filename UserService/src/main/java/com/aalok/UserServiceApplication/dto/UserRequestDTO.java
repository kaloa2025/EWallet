package com.aalok.UserServiceApplication.dto;

import com.aalok.UserServiceApplication.model.UserIdentifier;
import com.aalok.UserServiceApplication.model.UserType;
import com.aalok.UserServiceApplication.model.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
//@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDTO {
    private String name;
    @NotBlank(message = "contact can not be blank")
    private String contact;
    @NotBlank(message = "email can not be blank")
    private String email;
    private String address;
    private String dob;
//    @NotNull(message = "userIdentifier can not be blank")
    private UserIdentifier userIdentifier;
    @NotBlank(message = "userIdentifierValue can not be blank")
    private String userIdentifierValue;
    @NotBlank(message = "password can not be blank")
    private String password;

    public Users toUser() {
        return Users.builder()
                .name(this.name)
                .contact(this.contact)
                .email(this.email)
                .address(this.address)
                .dob(this.dob)
                .userIdentifierValue(this.userIdentifierValue)
                .userIdentifier(this.userIdentifier).
                isEnabled(true).
                isAccountNonExpired(true).
                isCredentialsNonExpired(true).
                isAccountNonLocked(true).
                userType(UserType.USER).
                build();
    }
}

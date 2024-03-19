package com.reservation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String roleType;
    private MultipartFile profilePicture;
}

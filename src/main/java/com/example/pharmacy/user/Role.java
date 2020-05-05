package com.example.pharmacy.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Serializable, GrantedAuthority {

    private Integer id;
    private String name;
    private String nameZh;
    private String description;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
}

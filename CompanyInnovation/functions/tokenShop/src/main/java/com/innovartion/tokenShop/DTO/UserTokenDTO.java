package com.innovartion.tokenShop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTokenDTO {
    private String userId;
    private int tokens;

}
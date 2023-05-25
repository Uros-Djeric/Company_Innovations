package com.innovation.manageShop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private int price;
}

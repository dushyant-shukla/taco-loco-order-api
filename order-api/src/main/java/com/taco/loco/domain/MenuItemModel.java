package com.taco.loco.domain;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MenuItemModel {

    @NonNull private long id;
    @NonNull private String name;
    @NonNull private float price;
}

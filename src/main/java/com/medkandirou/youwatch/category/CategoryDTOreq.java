package com.medkandirou.youwatch.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CategoryDTOreq {
    private Integer id;
    @NonNull private String name;
}

package com.hediyesilaozyurt.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceResponse {

    private String url;

    private String username;

    private String password;
}

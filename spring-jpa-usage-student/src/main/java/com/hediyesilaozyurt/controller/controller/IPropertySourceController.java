package com.hediyesilaozyurt.controller.controller;

import com.hediyesilaozyurt.config.DataSourceResponse;
import com.hediyesilaozyurt.config.Server;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;

import java.util.List;

public interface IPropertySourceController {

    public RootEntity<DataSourceResponse> getDataSource();

    public RootEntity<List<Server>> getServers();
}

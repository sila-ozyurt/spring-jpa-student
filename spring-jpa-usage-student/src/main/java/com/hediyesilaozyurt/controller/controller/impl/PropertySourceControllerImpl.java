package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.config.DataSourceResponse;
import com.hediyesilaozyurt.config.DataSourceProperties;
import com.hediyesilaozyurt.config.Server;
import com.hediyesilaozyurt.config.ServerConfig;
import com.hediyesilaozyurt.controller.controller.IPropertySourceController;
import com.hediyesilaozyurt.entities.soleResponseType.RootEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/api/property")
public class PropertySourceControllerImpl extends RestBaseController implements IPropertySourceController {

    @Autowired
    private DataSourceProperties globalProperties;

    @Autowired
    private ServerConfig serverConfig;

    @Override
    @GetMapping(path = "/get-servers")
    public RootEntity<List<Server>> getServers() {
        return ok(serverConfig.getServers());
    }

    @Override
    @GetMapping(path = "/datasource")
    public RootEntity<DataSourceResponse> getDataSource() {
        return ok(new DataSourceResponse(globalProperties.getUrl(), globalProperties.getUsername(),null));
    }


}

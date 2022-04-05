package com.udemy.springgraphql.graphql.resolvers.query;

import java.util.List;
import java.util.UUID;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CacowardsQueryResolver implements GraphQLQueryResolver {

    private WadService service;

    public CacowardsQueryResolver(WadService service) {
        super();
        this.service = service;
    }

    public List<Wad> cacowards(int count, int page) {
        log.info("Retrieving cacowards... count: {}, page: {}");
        List<Wad> cacowards = this.getCacowards(count, page);

        log.info("Retrieved cacowards: {}", cacowards);
        return cacowards;
    }

    private List<Wad> getCacowards(int count, int page) {
        return this.service.getCacowards(count, page);
    }

}

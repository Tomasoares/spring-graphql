package com.udemy.springgraphql.graphql.resolvers.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udemy.springgraphql.graphql.type.WadInput;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WadMutationResolver implements GraphQLMutationResolver {

    public UUID createWad(WadInput input) {
        return UUID.randomUUID();
    }

}

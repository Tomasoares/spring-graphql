package com.udemy.springgraphql.graphql.resolvers.subscription.util;

import com.udemy.springgraphql.graphql.type.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewSubscribe {

    private Review review;
    private UUID wadId;

}

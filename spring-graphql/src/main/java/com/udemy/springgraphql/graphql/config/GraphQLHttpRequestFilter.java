package com.udemy.springgraphql.graphql.config;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j
public class GraphQLHttpRequestFilter implements GraphQLServletListener {

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        Instant startTime = Instant.now();
        log.info("Request started in: {}", startTime);

        return new RequestCallback() {

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                Instant endTime = Instant.now();
                log.info("Request finished in: [}", endTime);
                log.info("Duration: {}", Duration.between(startTime, endTime));
                RequestCallback.super.onFinally(request, response);
            }
        };
    }

}

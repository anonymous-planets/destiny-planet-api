package com.planet.destiny.gateway.server.filter;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            log.info("GlobalFilter baseMessage >>>>>>>" + config.getBaseMessage());
            if((config.getPreLogger())) {
                log.info("GlobalFilter Start >>>>>>>" + exchange.getRequest());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.getPostLogger()) {
                    log.info("GlobalFilter End >>>>>>" + exchange.getResponse());
                }
            }));
        }));
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Config {
        private String baseMessage;
        private Boolean preLogger;
        private Boolean postLogger;
    }
}

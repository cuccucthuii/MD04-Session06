package ra.medicalgateway.filter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ipAddress = Optional.ofNullable(exchange.getRequest().getRemoteAddress())
                .map(LoggingFilter::resolveClientIp)
                .orElse("unknown");
        String path = exchange.getRequest().getPath().value();
        String method =
                exchange.getRequest().getMethod() != null
                        ? exchange.getRequest().getMethod().toString()
                        : "UNKNOWN";

        System.out.println(">>> [Medical Gateway Log] Client IP: " + ipAddress + " | Method: " + method
                + " | Path: " + path);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private static String resolveClientIp(InetSocketAddress remoteAddress) {
        InetAddress address = remoteAddress.getAddress();
        if (address != null) {
            return address.getHostAddress();
        }
        return remoteAddress.getHostString();
    }
}

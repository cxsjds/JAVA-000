package nio02.m.ma.ko.io.github.kimmking.gateway.router;

import java.util.List;

public interface HttpEndpointRouter {
    
    String route(List<String> endpoints);
    
}

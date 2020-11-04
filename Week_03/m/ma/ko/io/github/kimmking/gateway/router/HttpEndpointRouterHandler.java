package nio02.m.ma.ko.io.github.kimmking.gateway.router;

import java.util.List;

//实现路由
public class HttpEndpointRouterHandler implements HttpEndpointRouter {
    @Override
    public String route(List<String> endpoints) {
        if(endpoints.size() > 0 && endpoints.contains("hello")){
           return "/api/hello";
        }
        return "/nofund";
    }
}

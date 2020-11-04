package nio02.m.ma.ko.io.github.kimmking.gateway;


import nio02.m.ma.ko.io.github.kimmking.gateway.inbound.HttpInboundServer;

//这是第五课的主类作业
public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";
    
    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
        String proxyPort = System.getProperty("proxyPort","8888");
       
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServer);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

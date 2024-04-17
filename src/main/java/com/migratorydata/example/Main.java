package com.migratorydata.example;

import com.migratorydata.client.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {

        // create a MigratoryData client
        final MigratoryDataClient client = new MigratoryDataClient();

        client.setLogging(MigratoryDataLogLevel.TRACE, new File("me.txt"), 0);

        // attach the entitlement token
        client.setEntitlementToken("some-token");

        // Define the listener to handle live message and status notifications
        // In your application it is recommended to define a regular class
        // instead of the anonymous class we define here for concision
        client.setListener(new MigratoryDataListener() {

            public void onStatus(String status, String info) {
                System.out.println("Got Status: " + status + " - " + info);
            }

            public void onMessage(MigratoryDataMessage message) {
                System.out.println("Got Message: " + message);
            }

        });

        // Set client with nginx proxy (see dir proxies/nginx-proxy)
        client.setProxy(MigratoryDataProxyHandler.createHttpProxyHandler("127.0.0.1", 8800, null, null, null));


        // Set MigratoryData client with squid proxy and authentication (see dir proxies/squid-proxy)
        // client.setProxy(MigratoryDataProxyHandler.createHttpProxyHandler("127.0.0.1", 3128, "foo", "boo", null));        

        // Set client with socks proxy (see dir proxies/socks-proxy)
        // client.setProxy(MigratoryDataProxyHandler.createSocksProxyHandler("127.0.0.1", 1080, null));

        // Set client with socks proxy and authentication (see dir proxies/socks-proxy)
        // client.setProxy(MigratoryDataProxyHandler.createSocksProxyHandler("127.0.0.1", 1080, new Authenticator() {
        //     protected PasswordAuthentication getPasswordAuthentication() {
        //         return new PasswordAuthentication("foo", "boo".toCharArray()); 
        //     }
        // }));
        

        client.setEncryption(true);

        // set server to connect to the MigratoryData server
        client.setServers(new String[] {"demo.migratorydata.com"});

        // subscribe
        client.subscribe(Arrays.asList("/server/status"));

        // connect to the MigratoryData server
        client.connect();

        // publish a message every 3 seconds
        int count = 1;
        while (count++ < 10000000) {
            String content = "data - " + count;
            String closure = "id" + count;
            client.publish(new MigratoryDataMessage("/server/status", content, closure));

            Thread.sleep(5000);
        }

        // add a shutdown hook to catch CTRL-C and cleanly shutdown this client
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                client.disconnect();
            }
        });
    }
}
#### How to use  MigratoryData Java Client API with Http forward proxy

> Works with Client Java API v5

##### Prerequisites
Java Development Kit (JDK) 8+, Gradle 6+, Docker 

#### Clone Project

Clone the getting started project from GitHub using your IDE of choice or using the following command:
```bash
git clone https://github.com/migratorydata/getting-started-forwarding-proxy-java.git
```

In directory `proxies/nginx-proxy` you will find the configuration files for the Nginx proxy server. To run the demo proxy server, you need to have Docker installed on your machine.

Go to directory `proxies/nginx-proxy` and run the following command to build and start the Docker image:

```bash
docker compose build proxy
docker compose up
```

The proxy server will accept connections on port 8800.

#### Configure
Update the code snippet from the file `src/main/java/com/migratorydata/example/Main.java` to your needs (entitlement token, servers, subjects).

Configure Http proxy settings using method `setProxy` of the `MigratoryDataClient` class. The following code snippet shows how to configure the client to use an HTTP proxy server running on localhost and listening on port 8800:

```java
client.setProxy(MigratoryDataProxyHandler.createHttpProxyHandler("127.0.0.1", 8800, null, null, null));
```

#### Build & Run

Use the following commands to build and run your project:

```bash
./gradlew clean build
./gradlew run
```

#### Other Proxies

For other types of proxies, please refer to the directory `proxies` in the root of the project. `socks-proxy` contains an example of using a SOCKS proxy server, while `squid-proxy` contains an example of using a Squid proxy server with authentication.

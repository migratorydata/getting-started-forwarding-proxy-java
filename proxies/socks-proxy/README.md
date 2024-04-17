# Start a socks proxy which listen to port 1080 without authentication
docker run -d --name=httptoolkit-docker-tunnel -p 127.0.0.1:1080:1080 ghcr.io/httptoolkit/docker-socks-tunnel

# Set client with socks proxy
client.setProxy(MigratoryDataProxyHandler.createSocksProxyHandler("127.0.0.1", 1080, null));


---------

# Start a socks proxy which listen to port 1080 and set username and password
(https://github.com/tetafro/socks5/tree/master)

docker run \
--publish 1080:1080 \
--name socks5 \
tetafro/socks5 \
sh -c '/app/socks5 -username foo -password boo'

# Set client with socks proxy and authentication
client.setProxy(MigratoryDataProxyHandler.createSocksProxyHandler("127.0.0.1", 1080, new Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("foo", "boo".toCharArray()); 
    }
}));

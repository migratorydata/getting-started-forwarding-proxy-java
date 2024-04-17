# Start squid proxy which listen to port 3128 with username and password foo:boo
docker compose up

# Test proxy with curl
curl -I --proxy http://127.0.0.1:3128 https://www.google.com
# > HTTP/1.1 407 Proxy Authentication Required

curl -I --proxy http://foo:boo@127.0.0.1:3128 https://www.google.com
# > HTTP/1.1 200 Connection established

# Set MigratoryData client with squid proxy and authentication
client.setProxy(MigratoryDataProxyHandler.createHttpProxyHandler("127.0.0.1", 3128, "foo", "boo", null));

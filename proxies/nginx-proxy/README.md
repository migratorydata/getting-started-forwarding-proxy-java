# Set client with nginx proxy
client.setProxy(MigratoryDataProxyHandler.createHttpProxyHandler("127.0.0.1", 8800, null, null, null));

# Run the nginx proxy
docker compose up

# Test
curl https://migratorydata.com -svo /dev/null -x localhost:8800

# Rebuild image command
docker compose build proxy

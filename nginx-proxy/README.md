rebuild image
docker compose build proxy

Run the nginx proxy
docker compose up

Test
curl https://migratorydata.com -svo /dev/null -x localhost:8800
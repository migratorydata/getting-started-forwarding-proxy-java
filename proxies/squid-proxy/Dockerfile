FROM ubuntu/squid

COPY passwords /etc/squid/passwords
COPY squid.conf /etc/squid/squid.conf

RUN chown proxy:proxy /etc/squid/squid.conf /etc/squid/passwords
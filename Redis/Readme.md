## Install Redis on windows
https://redis.io/docs/getting-started/installation/install-redis-on-windows/#install-redis

### Start Redis
    sudo service redis-server start
    Starting redis-server: redis-server.

### Redis CLI
    redis-cli
    127.0.0.1:6379>

### Check Redis Data (no data found)
    127.0.0.1:6379> keys *
    (empty array)

### Check Redis Data (data found)
    127.0.0.1:6379> keys *
    1) "account:8e025c4f-3c24-4751-b2de-152bc2397e97"
    2) "account"

### Check Redis Data (session)
    127.0.0.1:6379> ttl account:8e025c4f-3c24-4751-b2de-152bc2397e97
    (integer) 3458 'this is session value in second
    

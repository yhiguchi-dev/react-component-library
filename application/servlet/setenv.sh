dbUser=${DB_USER:=purchase}
dbPassword=${DB_PASSWORD:=password}
dbHost=${DB_HOST:=localhost}
dbPort=${DB_PORT:=5432}
dbName=${DB_NAME:=purchase}

JAVA_OPTS="-DdbUser=${dbUser} -DdbPassword=${dbPassword} -DdbHost=${dbHost} -DdbPort=${dbPort} -DdbName=${dbName}"

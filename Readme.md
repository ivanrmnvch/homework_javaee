### 1 Database connect
#### 1. Install glassfish [youtube](https://www.youtube.com/watch?v=gAPses4BBmQ). Text format:
- Update Server:
  apt update && apt upgrade -y

- Create a System User (user creation is optional):
useradd -m -d /opt/glassfish6 -U -s /bin/false glassfish

- Install Java:
apt install default-jdk -y

- Install GlassFish:
cd /opt
wget https://www.eclipse.org/downloads/dow... -O glassfish6.zip
unzip glassfish6.zip
chown -R glassfish: /opt/glassfish6

- Create Systemd File:
nano /etc/systemd/system/glassfish.service
- Paste
```text
[Unit]
Description = GlassFish Server v6
After = syslog.target network.target

[Service]
User=glassfish
ExecStart=/opt/glassfish6/bin/asadmin start-domain
ExecReload=/opt/glassfish6/bin/asadmin restart-domain
ExecStop=/opt/glassfish6/bin/asadmin stop-domain
Type = forking

[Install]
WantedBy = multi-user.target
```

- systemctl daemon-reload
- systemctl start glassfish

- Access GlassFish:
http://server_ip:8080

- Configure GlassFish:
http://server_ip:4848

```text
/opt/glassfish6/bin/asadmin --port 4848 change-admin-password
[enter]
[enter]
[new password]
[retype password]
/opt/glassfish6/bin/asadmin --port 4848 enable-secure-admin
```

- systemctl restart glassfish

#### 2. DB connection [youtube](https://www.youtube.com/watch?v=iQRBGoKrtrg). Text format:
- localhost:4848 -> JDBC -> JDBC Connection Pools -> New:
  - Enter PoolName, example: myDababasePool.
  - Enter Resource Type = javax.sql.DataSource.
  - Enter Database Driver Vendor = postgresql.
- -> Next -> Additional Properties -> Enter:
  - User = your_database_user_name
  - Password = your_database_password

#### 3. localhost:4848 -> JDBC Connection Pools -> Additional Properties:
- URL = jdbc:postgresql://localhost:5432/your_database_name (otherwise connection to default database - postgres)
- Url = jdbc:postgresql://localhost:5432/your_database_name

#### 4. Add the following file to the project:
- PATH: src/main/webapp/WEB-INF/glassfish-resources.xml
- Paste
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE resources PUBLIC "-//GlassFish.org//DTD GlassFish Application Server 3.1 Resource Definitions//EN" "http://glassfish.org/dtds/glassfish-resources_1_5.dtd">
<resources>
<jdbc-resource enabled="true" jndi-name="jdbc/SecretDB" object-type="user" pool-name="MyPostgresDBPool"/>
<jdbc-connection-pool allow-non-component-callers="false" associate-with-thread="false"
                      connection-creation-retry-attempts="0"
                      connection-creation-retry-interval-in-seconds="10"
                      connection-leak-reclaim="false" connection-leak-timeout-in-seconds="0"
                      connection-validation-method="auto-commit"
                      datasource-classname="org.postgresql.ds.PGSimpleDataSource"
                      fail-all-connections="false" idle-timeout-in-seconds="300"
                      is-connection-validation-required="false" is-isolation-level-guaranteed="true"
                      lazy-connection-association="false" lazy-connection-enlistment="false"
                      match-connections="false" max-connection-usage-count="0"
                      max-pool-size="32" max-wait-time-in-millis="60000" name="MyPostgresDBPool"
                      non-transactional-connections="false" pool-resize-quantity="2"
                      statement-timeout-in-seconds="-1"
                      steady-pool-size="8"
                      validate-atmost-once-period-in-seconds="0"
                      wrap-jdbc-objects="false">
    <property name="ServerName" value="localhost"/>
    <property name="PortNumber" value="5432"/>
    <property name="DatabaseName" value="secret"/>
    <property name="User" value="postgres"/>
    <property name="Password" value="1234"/>
</jdbc-connection-pool>
</resources>
```

./asadmin start-domain

echo -------1. build maven project ---------
mvn clean package -DskipTests
echo -------2. build docker image config-server ---------
docker build -t config-server:lianglianglee   --network host  ConfigServer
echo -------3. build docker image eureka-server ---------
docker build -t edit-server:lianglianglee   --network host  EditServer
echo -------4. build docker image edit-server ---------
docker build -t eureka-server:lianglianglee   --network host  EurekaServer
echo -------5. build docker image user-server ---------
docker build -t user-server:lianglianglee   --network host  UserServer
echo -------6. build docker image zuul-server ---------
docker build -t zuul-server:lianglianglee   --network host  ZuulServer


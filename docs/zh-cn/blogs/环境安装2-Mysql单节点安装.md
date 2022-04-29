?> Docker版本 `ce` Mysql版本 `latest`

##### 卸载之前的docker

```bash
sudo yum remove -y docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

##### 使用阿里软件源安装docker

```bash 
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo 
sudo yum makecache all
sudo yum install -y docker-ce docker-ce-cli containerd.io
```

###### 启动docker

```bash
sudo systemctl enable docker   
sudo systemctl start docker   
sudo systemctl status docker   
```

##### 配置阿里镜像仓库

```bash
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://3fe1zqfu.mirror.aliyuncs.com"],
  "data-root":"/data/docker"
}
EOF
sudo cat /etc/docker/daemon.json
sudo systemctl daemon-reload
sudo systemctl restart docker              
```

##### 将`ispong`添加到docker组

```bash
sudo gpasswd -a ispong docker 
newgrp docker
sudo chmod a+rw /var/run/docker.sock
```

##### 创建mysql存储目录

```bash
sudo mkdir -p /data/mysql/data
sudo mkdir -p /data/mysql/conf.d
```

##### 安装mysql

```bash
docker run \
  --name isxcode-mysql \
  --privileged=true \
  --restart=always \
  -d \
  -p 30102:3306 \
  -e MYSQL_ROOT_PASSWORD=isxcode123 \
  -e MYSQL_DATABASE=isxcode-db \
  -v /data/mysql/data:/var/lib/mysql \
  -v /data/mysql/conf.d:/etc/mysql/conf.d \
  mysql
```

##### 登录mysql

```bash
docker exec -it isxcode-mysql bash
mysql -h localhost -u root -pisxcode123 -P 30102
```

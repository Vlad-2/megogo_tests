FROM jenkins/jenkins:lts

USER root

RUN apt-get update && apt-get install -y \
    git \
    maven \
    openjdk-17-jdk \
    docker.io \
    && apt-get clean

USER jenkins

COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/plugins.txt

COPY casc_configs/ /var/jenkins_home/casc_configs/
COPY jobs/ /var/jenkins_home/jobs/

ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc_configs

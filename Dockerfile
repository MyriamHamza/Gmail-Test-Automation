FROM ubuntu:latest

# Install dependencies
RUN apt-get update && \
    apt-get install -y curl unzip wget gnupg2 software-properties-common

# Install Java
RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update && \
    apt-get install -y openjdk-8-jdk

# Install Gradle
ENV GRADLE_VERSION=7.4
RUN wget -q https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip && \
    unzip gradle-$GRADLE_VERSION-bin.zip && \
    rm gradle-$GRADLE_VERSION-bin.zip && \
    mv gradle-$GRADLE_VERSION /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV GRADLE_HOME=/opt/gradle

# Add Gradle and Java to PATH
ENV PATH=$PATH:$GRADLE_HOME/bin:$JAVA_HOME/bin

# Install Selenium dependencies
RUN apt-get update && \
    apt-get install -y xvfb libxi6 libgconf-2-4

# Install Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable

# Install ChromeDriver
RUN LATEST=$(wget -q -O - http://chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget http://chromedriver.storage.googleapis.com/$LATEST/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/

WORKDIR /home/selenium/
CMD ["/bin/bash"]

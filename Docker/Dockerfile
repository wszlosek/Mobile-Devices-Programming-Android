FROM ubuntu:20.04
ENV TZ=Europe/Warsaw

#update ubuntu
RUN apt update
RUN apt upgrade -y

RUN apt install -y zip unzip curl
RUN useradd -ms /bin/bash wojciech
RUN adduser wojciech sudo

#install sdkman
USER wojciech
WORKDIR /home/wojciech
RUN curl -s "https://get.sdkman.io" | bash
RUN chmod a+x "$HOME/.sdkman/bin/sdkman-init.sh"

RUN /bin/bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install java 11.0.13.8.1-amzn"	
RUN /bin/bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install gradle 7.2"
RUN /bin/bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install kotlin 1.5.31"

ENV PATH=/home/wojciech/.sdkman/candidates/java/current/bin:$PATH
ENV PATH=/home/wojciech/.sdkman/candidates/gradle/current/bin:$PATH
ENV PATH=/home/wojciech/.sdkman/candidates/kotlin/current/bin:$PATH


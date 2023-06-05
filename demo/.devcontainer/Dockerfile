FROM amazonlinux:2023


# 開発言語に依存しない共通のプログラムの準備
# （Git for Windows にも入っているようなツールと useradd のための shadow-utils を入れておく）


RUN set -eux \
    && dnf install -y \
        bash-completion \
        diffutils \
        findutils \
        git \
        gzip \
        shadow-utils \
        tar \
        tig \
        unzip \
        vim \
        which \
    && dnf clean all


# 共通設定
# UID=1000 の一般ユーザを作成する
# （ユーザ名は任意だが、朝日ネットの既存環境に合わせて appdata としている）
RUN set -eux \
    && cp /usr/share/zoneinfo/Japan /etc/localtime \
    && useradd -u 1000 appdata


# Java環境の準備
RUN set -eux \
    && dnf install -y \
        java-17-amazon-corretto \
    && dnf clean all


WORKDIR /opt


# /opt/gradle の準備


ARG GRADLE_VERSION=8.1.1
ARG GRADLE_ARCHIVE_NAME="gradle-${GRADLE_VERSION}-bin.zip"
ARG GRADLE_ARCHIVE_URL="https://services.gradle.org/distributions/${GRADLE_ARCHIVE_NAME}"


RUN set -eux \
    && curl -fsSLO "${GRADLE_ARCHIVE_URL}" \
    && unzip "$GRADLE_ARCHIVE_NAME" \
    && rm -f "$GRADLE_ARCHIVE_NAME" \
    && ln -s "gradle-${GRADLE_VERSION}" gradle \
    && echo "export GRADLE_HOME=/opt/gradle" >>/etc/profile.d/gradle.sh \
    && echo "export PATH=\"\${GRADLE_HOME}/bin:\${PATH}\"" >>/etc/profile.d/gradle.sh


# /opt/kotlinc の準備


ARG KOTLIN_VERSION=1.8.21
ARG KOTLIN_ARCHIVE_NAME="kotlin-compiler-${KOTLIN_VERSION}.zip"
ARG KOTLIN_ARCHIVE_URL="https://github.com/JetBrains/kotlin/releases/download/v${KOTLIN_VERSION}/${KOTLIN_ARCHIVE_NAME}"


RUN set -eux \
    && curl -fsSLO "${KOTLIN_ARCHIVE_URL}" \
    && unzip "$KOTLIN_ARCHIVE_NAME" \
    && rm -f "$KOTLIN_ARCHIVE_NAME" \
    && mv kotlinc "kotlinc-${KOTLIN_VERSION}" \
    && ln -s "kotlinc-${KOTLIN_VERSION}" kotlinc \
    && echo "export KOTLIN_HOME=/opt/kotlinc" >>/etc/profile.d/kotlinc.sh \
    && echo "export PATH=\"\${KOTLIN_HOME}/bin:\${PATH}\"" >>/etc/profile.d/kotlinc.sh


USER appdata


WORKDIR /home/appdata


# See: https://github.com/corretto/corretto-docker/blob/main/17/jdk/al2023/Dockerfile
ENV LANG=C.UTF-8
ENV JAVA_HOME=/usr/lib/jvm/java-17-amazon-corretto


ENV DEVCONTAINER=true
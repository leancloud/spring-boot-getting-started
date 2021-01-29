# Java Getting started

A simple Java application, based on Spring Boot, packaged as a JAR file, for LeanEngine Java runtime.

Currently it only supports Java 8.

## Running Locally

Clone this repository and install dependencies:

```sh
git clone https://github.com/leancloud/spring-boot-getting-started.git
cd spring-boot-getting-started
mvn package
```

Fill application information in `local.sh`:

```sh
export LC_APP_ID=
export LC_APP_KEY=
export LEANCLOUD_APP_ID=
export LEANCLOUD_APP_KEY=
export LEANCLOUD_APP_MASTER_KEY=
```

Start the project locally:

```sh
./local.sh
```

Open http://localhost:3000 in your browser.

## Documentation

- [Java Web Hosting Guide](https://docs.leancloud.app/leanengine_webhosting_guide-java.html)
- [Java Cloud Function Guide](https://docs.leancloud.app/leanengine_cloudfunction_guide-java.html)
- [LeanStorage Java Guide](https://docs.leancloud.app/leanstorage_guide-java.html)
- [Java SDK API](https://leancloud.cn/api-docs/android/index.html)
- [lean-cli Guide](https://docs.leancloud.app/leanengine_cli.html)

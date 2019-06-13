[![Build Status][ci-img]][ci] [![Apache-2.0 license](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Applications for integration testing of Special Agent

## Requirements

- Apache Maven 3.6.1 (or higher)

- Oracle JDK 1.8 (or higher)

- GNU Make

## Installation

1. [Download Special Agent jar](https://github.com/opentracing-contrib/java-specialagent/#2111-stable).
    E.g.:
    ```bash
    wget -O opentracing-specialagent-1.2.0.jar "http://central.maven.org/maven2/io/opentracing/contrib/specialagent/opentracing-specialagent/1.2.0/opentracing-specialagent-1.2.0.jar"
    ```  
1. Modify [Makefile](./Makefile) by changing `specialagent_jar` variable to correct value of downloaded Special Agent jar 
1. Setup LS
    - If you use local instance of LS then export token as `LS_LOCAL_TOKEN` variable.
    E.g.:
    ```bash
    export LS_LOCAL_TOKEN=bla-bla-bla
    ```
    - If you use remote instance (https://app.lightstep.com) export token as `LS_TOKEN` variable.
    E.g.:
    ```bash
    export LS_TOKEN=bla-bla-bla
    ```
1. Go to one of the modules.
    E.g.:
    ```bash
    cd feign
    ```    
1. Run `make` to build module
   ```bash
   make
   ```
1. Run module with Special Agent jar
   - For local LS:
   ```bash
   make run-local
   ```   
   - For remote LS:
   ```bash
   make run
   ```
1. Go to LS explorer to see traces

Some of the modules require running additional services. 
For example it is required to start Redis server before run `jedis-2`, `jedis-3`, `lettuce`, `redisson`.

## License

[Apache 2.0 License](./LICENSE).

[ci-img]: https://travis-ci.org/malafeev/sa-apps.svg?branch=master
[ci]: https://travis-ci.org/malafeev/sa-apps
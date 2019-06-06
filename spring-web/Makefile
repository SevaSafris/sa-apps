.PHONY: build clean run

specialagent_jar := opentracing-specialagent-1.2.1-SNAPSHOT.jar

specialagent_jar_path := /Users/malafes/Downloads/${specialagent_jar}

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
		-Dls.componentName=spring-web \
		-Dls.collectorHost=collector.lightstep.com \
		-Dls.collectorProtocol=https \
		-Dls.collectorPort=443 \
		-Dls.accessToken=$(LS_TOKEN) \
		-Dsa.tracer=lightstep \
		-cp target/spring-web-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar_path) boot.App

run-travis-test:
	java \
		-Dls.componentName=spring-web \
		-Dsa.tracer=mock \
		-cp target/spring-web-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) boot.App

run-local:
	java \
        -Dls.componentName=spring-web \
        -Dls.collectorHost=localhost \
        -Dls.collectorProtocol=http \
        -Dls.collectorPort=8360 \
        -Dls.accessToken=${LS_LOCAL_TOKEN} \
        -Dsa.tracer=lightstep \
        -cp target/spring-web-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar_path) boot.App

run-solo:
	java \
		-Dsa.tracer=mock \
		-Dsa.log.level=FINER \
		-Dsa.tracer.plugins.enable=false \
		-Dsa.instrumentation.plugins.enable=false \
		-Dsa.instrumentation.plugin.spring-web.enable=true \
		-cp target/spring-web-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar_path) boot.App

run-no-agent:
	java -cp target/spring-web-1.0-SNAPSHOT.jar boot.App
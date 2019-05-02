.PHONY: build clean run

specialagent_jar := /Users/malafes/Downloads/opentracing-specialagent-1.1.1-SNAPSHOT.jar

build:
	mvn clean package -DskipTests

clean:
	mvn clean

run:
	java \
	    -Dls.componentName=spring-web \
	            -Dls.collectorHost=localhost \
                -Dls.collectorProtocol=http \
                -Dls.collectorPort=8360 \
                -Dls.accessToken=eyJhbGciOiJIUzI1NiIsImtpZCI6IjIwMTktMDMtMDEiLCJ0eXAiOiJKV1QifQ.eyJzY29wZSI6eyJjb2xsZWN0b3IiOnsicmVhZHdyaXRlIjp0cnVlfSwiZGV2bW9kZSI6eyJyZWFkd3JpdGUiOnRydWV9fSwiYXVkX2lkIjoibzZqemdudGZpbWxpcDNrem1oZTRvMmJrYXJtMjJyeXZwZDR0ZHJ5bWhraDYzY21lIiwidXNlcl9pZCI6NzQzLCJhdWQiOiJPcGVuVHJhY2luZyBDb250cmFjdG9ycyIsImV4cCI6MTU4NzEyNDk1NSwianRpIjoiNG4yc3dtcWZmbDVidnRsdnFtdHpjeWw0cmZrb2pkM3FvaWZhaTdxNW1qZGJ4eW5zIiwiaWF0IjoxNTU1NTg4OTU1LCJpc3MiOiJsaWdodHN0ZXAuY29tIn0.h6AGT9bDW9rAILuwy-lWxACeDxC9H8BYNq4tGgJq30g \
                -Dsa.tracer=lightstep \
		-cp target/spring-webmvc-1.0-SNAPSHOT.jar -javaagent:$(specialagent_jar) org.springframework.boot.loader.JarLauncher

run-no-agent:
	java -cp target/spring-webmvc-1.0-SNAPSHOT.jar org.springframework.boot.loader.JarLauncher

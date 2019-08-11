FROM confluentinc/cp-kafka-connect:3.2.0

WORKDIR /kafka-connect
COPY config config
COPY target target

VOLUME /kafka-connect/config
VOLUME /kafka-connect/offsets

CMD CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')" connect-standalone config/worker.properties config/config.properties
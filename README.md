# Axway Notifier

Sandbox Solace-Axway-Agent Notifier Service for Testing and Demos

* Subscribes itself as MQTT client to Solace Message Broker (optional)
* Exposes REST-Endpoint to receive notification messages (optional)
* Listens on several topics (see `application.yml`)
* Transforms received messages to SLACK message and publishes them to SLACK via HTTP POST 

## Configuration
Define location of environment definition in either
  * `java -Dmicronaut.config.files="local-env/application.yml" notifier.jar`
  * as environment variable `export MICRONAUT_CONFIG_FILES=local-env/application.yml`

## Build Pipeline / GitHub Actions
* Builds and tests 
  * Test will publish test messages to Slack Channels
* Creates Docker Container
* Pushes Container to GitHub container repository `ghcr.io/solace-iot-team/axway-slack-notifier:latest`

## Micronaut 3.0.2 Documentation

- [User Guide](https://docs.micronaut.io/3.0.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.0.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.0.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

## Feature mqtt documentation

- [Micronaut MQTT v5 Messaging documentation](https://micronaut-projects.github.io/micronaut-mqtt/latest/guide/index.html)


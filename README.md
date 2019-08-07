# maven-dadapush-notification-plugin

send notification to DaDaPush via Apache Maven

## Usage

required configuration
- `channelToken`: please go [DaDaPush](https://www.dadapush.com) and create new channel.
- `title`: message title, length 0~50
- `content`: message content, length 0~500

optional configuration
- `basePath`: default is https://www.dadapush.com
- `failOnError`: if set true, when send fail, will throw exception.


```xml
  <plugin>
    <groupId>com.dadapush</groupId>
    <artifactId>dadapush-maven-plugin</artifactId>
    <version>${project.version}</version>
    <configuration>
      <basePath>https://www.dadapush.com</basePath>
      <channelToken>YOUR_CHANNEL_TOKEN</channelToken>
      <title>${project.name}</title>
      <content>send test notification from ${project.build.finalName}</content>
      <failOnError>true</failOnError>
    </configuration>
  </plugin>
```

or advanced usage

```xml
<plugin>
  <groupId>com.dadapush</groupId>
  <artifactId>dadapush-maven-plugin</artifactId>
  <version>${project.version}</version>
  <executions>
    <execution>
      <id>message-compile</id>
      <phase>compile</phase>
      <goals>
        <goal>message</goal>
      </goals>
      <configuration>
        <basePath>https://www.dadapush.com</basePath>
        <channelToken>YOUR_CHANNEL_TOKEN</channelToken>
        <title>${project.name}</title>
        <content>send compile notification from ${project.build.finalName}</content>
        <failOnError>true</failOnError>
      </configuration>
    </execution>
    <execution>
      <id>message-package</id>
      <phase>package</phase>
      <goals>
        <goal>message</goal>
      </goals>
      <configuration>
        <basePath>https://www.dadapush.com</basePath>
        <channelToken>YOUR_CHANNEL_TOKEN</channelToken>
        <title>${project.name}</title>
        <content>send package notification from ${project.build.finalName}</content>
        <failOnError>true</failOnError>
      </configuration>
    </execution>
    <execution>
      <id>message-test</id>
      <phase>test</phase>
      <goals>
        <goal>message</goal>
      </goals>
      <configuration>
        <basePath>https://www.dadapush.com</basePath>
        <channelToken>YOUR_CHANNEL_TOKEN</channelToken>
        <title>${project.name}</title>
        <content>send test notification from ${project.build.finalName}</content>
        <failOnError>true</failOnError>
      </configuration>
    </execution>
    <execution>
      <id>message-install</id>
      <phase>install</phase>
      <goals>
        <goal>message</goal>
      </goals>
      <configuration>
        <basePath>https://www.dadapush.com</basePath>
        <channelToken>YOUR_CHANNEL_TOKEN</channelToken>
        <title>${project.name}</title>
        <content>send install notification from ${project.build.finalName}</content>
        <failOnError>true</failOnError>
      </configuration>
    </execution>
  </executions>
</plugin>
```
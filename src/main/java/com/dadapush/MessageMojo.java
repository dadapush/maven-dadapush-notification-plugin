package com.dadapush;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.dadapush.client.ApiClient;
import com.dadapush.client.ApiException;
import com.dadapush.client.Configuration;
import com.dadapush.client.api.DaDaPushMessageApi;
import com.dadapush.client.model.MessagePushRequest;
import com.dadapush.client.model.ResultOfMessagePushResponse;
import java.net.URL;
import java.util.Objects;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@SuppressWarnings("unused")
@Mojo(name = "message")
public class MessageMojo extends AbstractMojo {

  @Parameter(property = "message.basePath", defaultValue = "https://www.dadapush.com")
  private URL basePath;

  @Parameter(property = "message.channelToken")
  private String channelToken;

  @Parameter(property = "message.title", defaultValue = "(not set title)")
  private String title;

  @Parameter(property = "message.content", defaultValue = "(not set content)")
  private String content;

  @Parameter(property = "message.failOnError", defaultValue = "false")
  private Boolean failOnError;

  public void execute()
      throws MojoExecutionException {

    ApiClient apiClient = Configuration.getDefaultApiClient();
    getLog().debug("basePath="+basePath);
    getLog().debug("title="+title);
    getLog().debug("content="+content);
    apiClient.setBasePath(Objects.requireNonNull(basePath).toString());
    DaDaPushMessageApi apiInstance = new DaDaPushMessageApi(apiClient);
    MessagePushRequest body = new MessagePushRequest();
    body.setTitle(title);
    body.setContent(content);
    body.setNeedPush(true);
    try {
      ResultOfMessagePushResponse result = apiInstance.createMessage(body, Objects.requireNonNull(channelToken));
      if (result.getCode() == 0) {
        getLog().info("send notification success, messageId=" + result.getData().getMessageId());
      } else {
        getLog()
            .warn("send notification fail, detail: " + result.getCode() + " " + result.getErrmsg());
      }
    } catch (ApiException e) {
      if (!failOnError) {
        getLog().error("send notification fail", e);
      } else {
        e.printStackTrace();
        throw new MojoExecutionException("send notification fail", e);
      }

    }

  }
}

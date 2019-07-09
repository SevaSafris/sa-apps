/**
 * Copyright 2017-2018 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package boot;

import io.opentracing.util.GlobalTracer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class Receiver {

  private final List<String> receivedMessages = new ArrayList<>();

  @StreamListener(Sink.INPUT)
  public void receive(String message) {
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("No active span");
      System.exit(-1);
    }
    receivedMessages.add(message);
  }

  public List<String> getReceivedMessages() {
    return receivedMessages;
  }

}

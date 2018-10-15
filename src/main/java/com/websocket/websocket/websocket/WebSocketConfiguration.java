package com.websocket.websocket.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {


    /*
    * Here we define the endpoint, that our clients will use to connect to the server.
    * So, in our case the URL for connection will be http://localhost:8080/socket/.
    * Also we allow server to receive requests from any origin. And we told the we will
     * use not “clean” websockets, but with SockJS.
    * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /*
    * What we say here — is an app prefix. So, when our client will send message through socket,
    * the URL to send will look approximately like this: http://localhost:8080/app/…/…
    * And also we said that for now we will have just one subscription — /chat. So clients will
     * subscribe to this subscription and will wait from messages from the server.
    * */

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/chat");
    }
}

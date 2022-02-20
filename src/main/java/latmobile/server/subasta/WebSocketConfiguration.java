package latmobile.server.subasta;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
        //registry.enableStompBrokerRelay(null)
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("/stomp").setAllowedOrigins("http://localhost:8080").setAllowedOrigins("http://subastas.latmobile.com").setAllowedOrigins("http://subastas.latmobile.com:80").withSockJS();
    	registry.addEndpoint("/stomp").setAllowedOriginPatterns("*").withSockJS();
    }
}

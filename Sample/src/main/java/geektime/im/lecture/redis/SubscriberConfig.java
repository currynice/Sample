package geektime.im.lecture.redis;

import geektime.im.lecture.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@EnableCaching
@Configuration
public class SubscriberConfig {
    @Autowired
    private RedisTemplate redisTemplate;

    private static int num = 0;


    @Autowired
    private NewMessageListener newMessageListener;

    //消息-订阅

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        if (0==num) {
            container.addMessageListener(new MessageListenerAdapter(newMessageListener), topic());
        }

        return container;
    }


    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(Constants.WEBSOCKET_MSG_TOPIC);
    }


}

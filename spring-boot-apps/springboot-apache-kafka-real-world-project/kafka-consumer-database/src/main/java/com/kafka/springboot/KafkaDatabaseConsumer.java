package com.kafka.springboot;

import com.kafka.springboot.entity.WikimediaData;
import com.kafka.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(topics="wikimedia_recentchange",groupId="myGroup")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message receiveddd->%s",eventMessage));
        WikimediaData wikimediaData=new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        LOGGER.info("Saving event to DB: {}", eventMessage);

        wikimediaDataRepository.save(wikimediaData);
        LOGGER.info("Saved event to DB: {}", eventMessage);
    }


}

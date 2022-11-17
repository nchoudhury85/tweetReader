package com.tweet.tweetReader;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tweet.tweetReader.data.UserLabel;
import com.tweet.tweetReader.data.UserLabelRepository;

@SpringBootApplication
public class TweetReaderApplication {

	@Autowired
	private EntityManager em;
	
	@Bean UserLabelRepository getUsrLabelRepository() {
		return new UserLabelRepository(UserLabel.class, em);
	}

	public static void main(String[] args) {
		SpringApplication.run(TweetReaderApplication.class, args);
	}

}

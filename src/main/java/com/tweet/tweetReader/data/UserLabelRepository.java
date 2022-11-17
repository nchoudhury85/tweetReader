package com.tweet.tweetReader.data;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class UserLabelRepository extends SimpleJpaRepository<UserLabel,String>{

	public UserLabelRepository(Class<UserLabel> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
	}

}

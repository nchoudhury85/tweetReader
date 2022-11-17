package com.tweet.tweetReader.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tweet.tweetReader.data.UserLabel;
import com.tweet.tweetReader.data.UserLabelRepository;

@RestController
@RequestMapping(value="users")
public class TweetReadController {

	@Autowired
	private UserLabelRepository userRepository;
	
	@PostMapping(value="/")
	public String saveUserLabel(@RequestBody UserLabel userLabel) {
		userRepository.save(userLabel);
		return "User Label details saved!!!";
	}

	@GetMapping(value="/")
	public List<UserLabel> loadMultipleUsers() {
		String fPath = "D:\\\\TweetCollect_Project\\userDetail.txt";
		List<UserLabel> userLabels = new ArrayList<>();
		Path path = Paths.get(fPath);

	    try {
			BufferedReader reader = Files.newBufferedReader(path);
			while (reader.ready()) {
				String line = reader.readLine();
				if(line.contains(":")) {
					String[] lineParts = line.split(":");
					UserLabel uLabel = new UserLabel();
					uLabel.setUserName(lineParts[0]);
					uLabel.setPassword(lineParts[1]);
					uLabel.setLabels(lineParts[2]);
					userLabels.add(userRepository.save(uLabel));
				}
			}
	    }catch (IOException e) {
			System.out.println("Failed to read file!!!");
		}
	    return userLabels;
	}
	
	@GetMapping(value="/{user}")
	public String getUserLabel(@PathVariable String user) {
		Optional<UserLabel> users = userRepository.findById(user);
		if(users.isPresent()) {
			return "Labels : "+users.get().getLabels();
		}
		return "No Label details found!!!";
	}
	
	@GetMapping(value="/labels/{hashLabel}")
	public List<String> findTweetsByLabel(@PathVariable String hashLabel) {
			Map<String, String> urlVariables = new HashMap<String, String>();
			urlVariables.put("hashLabel", hashLabel);
			ResponseEntity<List> response = new RestTemplate().getForEntity("http://localhost:8050/twitter/labels/{hashLabel}", List.class,urlVariables);
			List<String> tweets = response.getBody();
			return tweets;
	}
	
	@GetMapping(value="/load/multiple")
	public List<UserLabel> saveMultipleUsers(@RequestParam String fPath) {
		List<UserLabel> userLabels = new ArrayList<>();
		Path path = Paths.get(fPath);

	    try {
			BufferedReader reader = Files.newBufferedReader(path);
			while (reader.ready()) {
				String line = reader.readLine();
				if(line.contains(":")) {
					String[] lineParts = line.split(":");
					UserLabel uLabel = new UserLabel();
					uLabel.setUserName(lineParts[0]);
					uLabel.setPassword(lineParts[1]);
					uLabel.setLabels(lineParts[2]);
					userLabels.add(userRepository.save(uLabel));
				}
			}
	    }catch (IOException e) {
			System.out.println("Failed to read file!!!");
		}
	    return userLabels;
	}
}

package com.colanator.forum;

import com.colanator.forum.controller.ForumController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForumApplicationTests {

	@Autowired
	private ForumController forumController;

	@Test
	public void contextLoads() {
		assert(forumController != null);
	}
}

package org.kazi.toufique.taskmgr;

import java.util.List;

import org.kazi.toufique.taskmgr.model.ParentTask;
import org.kazi.toufique.taskmgr.repository.ParentTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
// @EnableAutoConfiguration
@ComponentScan(basePackages = { "org.kazi.toufique.taskmgr" })
@EnableJpaRepositories(basePackages = "org.kazi.toufique.taskmgr.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "org.kazi.toufique.taskmgr.model")
public class TaskManagerApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger("TaskManagerApplication");

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Override
	public void run(String... args) throws Exception {

		List<ParentTask> list = (List<ParentTask>) parentTaskRepository.findAll();

		if (CollectionUtils.isEmpty(list)) {
			LOGGER.info("init database start...");
			ParentTask t1 = new ParentTask();
			t1.setParentTaskName("PARENT_TASK1");
			ParentTask t2 = new ParentTask();
			t2.setParentTaskName("PARENT_TASK2");

			parentTaskRepository.save(t1);
			parentTaskRepository.save(t2);
			LOGGER.info("init database done...");
		} else {
			LOGGER.info("database initialized...");
		}
	}

}

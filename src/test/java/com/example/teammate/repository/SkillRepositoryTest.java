package com.example.teammate.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.teammate.entity.Skill;

@SpringBootTest
public class SkillRepositoryTest {

    @Autowired
    private SkillRepository skillRepository;

    private static final String[] skillNames = {
            "Android", "AWS RDS", "AWS S3",
            "C", "C#", "C++",
            "Django", "Docker",
            "Elasticsearch",
            "Flask", "Flutter",
            "Go", "GraphQL",
            "Hadoop", "HTML/CSS",
            "iOS",
            "Java", "JavaScript", "Jenkins", "JPA", "JSP", "jQuery",
            "Kafka", "Kotlin", "Kubernetes",
            "Linux",
            "MyBatis", "MSSQL", "MySQL",
            "Node.js",
            "OracleDB",
            "Pandas", "PHP", "Pytorch",
            "React", "React Native", "Redis", "Redux", "Rust", "Ruby",
            "Scala", "Spring", "SpringBoot", "Swift",
            "Tensorflow", "Terraform", "TypeScript",
            "Vue.js"
    };

    @Test
    public void insertSkill() {
        for (String skillName : skillNames) {
            Skill skill = new Skill();
            skill.setSkillName(skillName);
            skillRepository.save(skill);
        }
    }
}

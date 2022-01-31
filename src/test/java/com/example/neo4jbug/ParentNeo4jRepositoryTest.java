package com.example.neo4jbug;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ParentNeo4jRepositoryTest {

    @Autowired
    ParentNeo4jRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @AfterEach
    public void tearDow() {
        repository.deleteAll();
    }

    @Test
    public void itUpdatesAndFetchesParentNodes() {
        ChildNode savedChildNode;

        ParentNode unsavedParentNode = ParentNode.builder()
                .childNodes(Set.of(ChildNode.builder().name("child node 1").build()))
                .build();

        ParentNode savedParentNode = repository.save(unsavedParentNode);
        assertThat(savedParentNode.getId()).isNotNull();
        assertThat(savedParentNode.getChildNodes()).hasSize(1);

        savedChildNode = savedParentNode.getChildNodes().stream().findFirst().get();
        assertThat(savedChildNode.getName()).isEqualTo("child node 1");

        ParentNode parentNodeToUpdate = savedParentNode.toBuilder()
                .childNodes(Set.of(ChildNode.builder().name("child node 2").build()))
                .build();

        ParentNode updatedParentNode = repository.save(parentNodeToUpdate);
        assertThat(updatedParentNode.getChildNodes()).hasSize(1);

        savedChildNode = updatedParentNode.getChildNodes().stream().findFirst().get();
        assertThat(savedChildNode.getName()).isEqualTo("child node 2");

        ParentNode fetchedParentNode = repository.findById(updatedParentNode.getId()).get();
        assertThat(fetchedParentNode.getChildNodes()).hasSize(1);
    }

}

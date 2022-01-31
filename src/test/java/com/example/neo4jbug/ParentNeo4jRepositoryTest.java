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
        ParentNode unsavedParentNode = new ParentNode();

        ChildNode childNode1 = new ChildNode();
        childNode1.setName("child node 1");

        unsavedParentNode.setChildNodes(Set.of(childNode1));

        ParentNode savedParentNode = repository.save(unsavedParentNode);
        assertThat(savedParentNode.getId()).isNotNull();
        assertThat(savedParentNode.getChildNodes()).hasSize(1);

        ChildNode savedChildNode;
        savedChildNode = savedParentNode.getChildNodes().stream().findFirst().get();
        assertThat(savedChildNode.getName()).isEqualTo("child node 1");

        ChildNode childNode2 = new ChildNode();
        childNode2.setName("child node 2");

        ParentNode parentNodeToUpdate = new ParentNode();
        parentNodeToUpdate.setId(savedParentNode.getId());
        parentNodeToUpdate.setChildNodes(Set.of(childNode2));

        ParentNode updatedParentNode = repository.save(parentNodeToUpdate);
        assertThat(updatedParentNode.getChildNodes()).hasSize(1);

        savedChildNode = updatedParentNode.getChildNodes().stream().findFirst().get();
        assertThat(savedChildNode.getName()).isEqualTo("child node 2");

        ParentNode fetchedParentNode = repository.findById(updatedParentNode.getId()).get();
        assertThat(fetchedParentNode.getChildNodes()).hasSize(1);
    }

}

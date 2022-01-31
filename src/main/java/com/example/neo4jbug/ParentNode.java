package com.example.neo4jbug;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;

@Node
public class ParentNode {

    @Id
    @GeneratedValue
    private Long id;

    private Set<ChildNode> childNodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ChildNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Set<ChildNode> childNodes) {
        this.childNodes = childNodes;
    }
}

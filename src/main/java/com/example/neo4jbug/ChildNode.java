package com.example.neo4jbug;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
@Builder
public class ChildNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

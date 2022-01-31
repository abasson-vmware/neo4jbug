package com.example.neo4jbug;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;

@Node
@Data
@Builder(toBuilder = true)
public class ParentNode {
    @Id
    private Long id;
    private Set<ChildNode> childNodes;
    @Version
    Long version;
}

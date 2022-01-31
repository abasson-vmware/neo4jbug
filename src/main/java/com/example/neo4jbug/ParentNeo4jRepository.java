package com.example.neo4jbug;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ParentNeo4jRepository extends Neo4jRepository<ParentNode, Long> {
}

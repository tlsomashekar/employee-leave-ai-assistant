package com.java.example.springai.config;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.vectorstore.VectorStore;

@Configuration
public class KnowledgeConfig {

    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore store =
                SimpleVectorStore.builder(embeddingModel).build();

        store.add(List.of(
            new Document("""
                Annual leave policy:
                Full-time employees receive 18 annual leave days per year.
                Leave must be approved by the employee's manager.
                """),
            new Document("""
                Leave carry-forward policy:
                Employees can carry forward a maximum of 5 unused
                annual leave days into the next calendar year.
                """),
            new Document("""
                Sick leave policy:
                Employees should notify their manager when taking sick leave.
                A medical certificate may be required for extended absence.
                """)
        ));

        return store;
    }
}
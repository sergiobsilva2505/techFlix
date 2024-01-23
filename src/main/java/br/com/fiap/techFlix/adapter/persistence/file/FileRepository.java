package br.com.fiap.techFlix.adapter.persistence.file;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FileRepository extends ReactiveCrudRepository<FileDocument, String> {
}

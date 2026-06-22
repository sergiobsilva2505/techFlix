package br.com.fiap.techflix.adapter.persistence.file;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FileRepository extends ReactiveCrudRepository<FileDocument, String> {
}

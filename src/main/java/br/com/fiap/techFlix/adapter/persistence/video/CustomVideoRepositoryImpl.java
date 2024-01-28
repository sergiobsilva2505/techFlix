package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.adapter.web.Operation;
import br.com.fiap.techFlix.application.ports.VideoSearchPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;

public class CustomVideoRepositoryImpl implements CustomVideoRepository {

    private final MongoTemplate mongoTemplate;

    public CustomVideoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<VideoDocument> search(VideoSearchPort videoSearchPort) {
        Query query = new Query();

        if (videoSearchPort.hasTitle()) {
            Pattern pattern = Pattern.compile(".*%s.*".formatted(videoSearchPort.title()), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }

        if (videoSearchPort.hasCategoryName()) {
            Pattern pattern = Pattern.compile(".*%s.*".formatted(videoSearchPort.categoryName()), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("category.name").regex(pattern));
        }

        if (videoSearchPort.hasPublicationDate()) {
            if (Operation.GTE.equals(videoSearchPort.publicationDateOperation())) {
                query.addCriteria(Criteria.where("publicationDate").gte(videoSearchPort.publicationDate()));
            } else if (Operation.LTE.equals(videoSearchPort.publicationDateOperation())) {
                query.addCriteria(Criteria.where("publicationDate").lte(videoSearchPort.publicationDate().atTime(LocalTime.MAX)));
            }
        }

        long count = mongoTemplate.count(query, VideoDocument.class);

        PageRequest pageable = PageRequest.of(videoSearchPort.page(), videoSearchPort.size());
        query.with(pageable);

        List<VideoDocument> videoDocuments = mongoTemplate.find(query, VideoDocument.class);

        return PageableExecutionUtils.getPage(videoDocuments, pageable, () -> count);
    }
}

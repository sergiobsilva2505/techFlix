package br.com.fiap.techFlix.adapter.persistence.video;

import org.springframework.data.mongodb.repository.*;

import java.util.List;

public interface VideoRepository extends MongoRepository<VideoDocument, String> {

    @Query("{ '_id': ?0 }")
    @Update("{ $inc: { 'details.views': 1 } }")
    void addView(String id);

    @Aggregation(pipeline = {
            "{ $match: { 'categories.name': { $in: ?1 } } }",
            "{ $project: { '_id': 1, 'title': 1, 'description': 1, 'categories': 1, 'details': 1, 'publicationDate': 1, 'score': { $size: { $setIntersection: [ '$categories.name', ?1 ] } } } }",
            "{ $sort: { 'score': -1, 'details.views': -1 } }",
    })
    List<VideoDocument> getRecommendations(String userId, List<String> categoriesNames);
}

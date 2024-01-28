package br.com.fiap.techFlix.adapter.persistence.video;

import org.springframework.data.mongodb.repository.*;

import java.util.List;

public interface VideoRepository extends MongoRepository<VideoDocument, String> {

    @Query("{ '_id': ?0 }")
    @Update("{ $inc: { 'details.views': 1 } }")
    void addView(String id);

    @Query("{ '_id': ?0 }")
    @Update("{ $inc: { 'details.likes': 1 } }")
    void addLike(String id);

    @Query("{ '_id': ?0 }")
    @Update("{ $inc: { 'details.likes': -1 } }")
    void removeLike(String id);

    List<VideoDocument> findAllByOrderByDetails_LikesDescDetails_ViewsDesc();

    default List<VideoDocument> getRecommendations() {
        return findAllByOrderByDetails_LikesDescDetails_ViewsDesc();
    }

    @Aggregation(pipeline = {
            "{ $match: { 'categories.name': { $in: ?0 } } }",
            "{ $project: { '_id': 1, 'title': 1, 'description': 1, 'categories': 1, 'details': 1, 'publicationDate': 1, 'score': { $size: { $setIntersection: [ '$categories.name', ?0 ] } } } }",
            "{ $sort: { 'score': -1, 'details.likes': -1, 'details.views': -1 } }",
            "{ $limit: 10 }"
    })
    List<VideoDocument> getRecommendations(List<String> categoriesNames);
}
